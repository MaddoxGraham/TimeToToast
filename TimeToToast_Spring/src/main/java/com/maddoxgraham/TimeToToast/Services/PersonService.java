package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.Config.UserAuthProvider;
import com.maddoxgraham.TimeToToast.DTOs.CredentialsDto;
import com.maddoxgraham.TimeToToast.DTOs.PersonDto;
import com.maddoxgraham.TimeToToast.DTOs.SignUpDto;
import com.maddoxgraham.TimeToToast.Exception.AppException;
import com.maddoxgraham.TimeToToast.Exception.UserNotFoundException;
import com.maddoxgraham.TimeToToast.Mappers.PersonMapper;
import com.maddoxgraham.TimeToToast.Models.*;
import com.maddoxgraham.TimeToToast.Models.Enums.Role;
import com.maddoxgraham.TimeToToast.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final PersonMapper personMapper;
    private final UserEventRoleRepository userEventRoleRepository;
    private final EventRepository eventRepository;
    private final UserTaskRepository userTaskRepository;
    private final TaskRepository taskRepository;

    //find a person
    public Person findPersonByIdPerson(Long idPerson) {
        return personRepository.findByidPerson(idPerson).orElseThrow(() -> new UserNotFoundException("User n° " + idPerson + " was not found"));
    }

    // Delete a person by ID and return the deleted person
    @Transactional
    public Person deletePersonByIdPerson(Long idPerson, Long idEvent){
        userEventRoleRepository.deleteByPerson_IdPersonAndEvent_IdEvent(idPerson, idEvent);
        Optional<Person> personOptional = personRepository.findById(idPerson);
        if (personOptional.isPresent()) {
            Person personToDelete = personOptional.get();
            List<Task> taskList = taskRepository.findByEvent_IdEvent(idEvent);
            for(Task task: taskList){
                Optional<UserTask> userTaskOpt = userTaskRepository.findByUserTaskKey_IdTaskAndUserTaskKey_IdPerson(task.getIdTask(), personToDelete.getIdPerson());
                if(userTaskOpt.isPresent()){
                    UserTask userTask = userTaskOpt.get();
                    userTaskRepository.deleteById(userTask.getUserTaskKey());
                }
            }
            if (Role.GUEST.equals(personToDelete.getRole())){
                personRepository.deleteByIdPerson(idPerson);
            }
            return personToDelete;
        } else {
            throw new EntityNotFoundException("Person with ID " + idPerson + " not found");
        }
    }

    public PersonDto verifyGuest(String email) {
        Person person = personRepository.findByEmail(email)
                .orElseThrow(() -> new AppException("Personne inconnue", HttpStatus.NOT_FOUND));
        return personMapper.toPersonDto(person);
    }

    public PersonDto addDetailsToGuest(Long idPerson, String firstName, String lastName) {
        Optional<Person> personOpt = personRepository.findByidPerson(idPerson);
        if(personOpt.isPresent()){
            Person person = personOpt.get();
            person.setFirstName(firstName);
            person.setLastName(lastName);
            personRepository.save(person);
            PersonDto personDto = personMapper.toPersonDto(person);
            return personDto;
        }
        return null;
    }

    public PersonDto fromGuestToUser(Long idPerson, PersonDto personDto) {
        Optional<Person> personOpt = personRepository.findByidPerson(idPerson);
        if(personOpt.isPresent()){
            Person person = personOpt.get();
            person.setLastName(personDto.getLastName());
            person.setFirstName(personDto.getFirstName());
            person.setLogin(personDto.getLogin());
            person.setRole(Role.USER);
            person.setPassword(personDto.getPassword());
            person.setEmail(personDto.getEmail());
            person.setPhone(personDto.getPhone());
            person.setAdresse(personDto.getAdresse());
            person.setVille(personDto.getVille());
            person.setCp(personDto.getCp());
            person.setBirthday(personDto.getBirthday());
            person.setToken("");

            return personMapper.toPersonDto(person);
        }
        return null;
    }

    public PersonDto addRefreshToken(Long idPerson, String refreshToken) {
        Optional<Person> personOpt = personRepository.findByidPerson(idPerson);
        if(personOpt.isPresent()){
            Person person = personOpt.get();
            person.setRefreshToken(refreshToken);
            personRepository.save(person);
            return personMapper.toPersonDto(person);
        }
        return null;
    }

    // SERVICE RELATIF AUX USERS
    public PersonDto login(CredentialsDto credentialsDto){
      Person person = personRepository.findUserByLogin(credentialsDto.login())
                .orElseThrow(() -> new AppException("Unknown User", HttpStatus.NOT_FOUND));
      if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()),
              person.getPassword())){
          return personMapper.toPersonDto(person);
      }
      throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public PersonDto register(SignUpDto signUpDto){
        Optional<Person> PersonOpt = personRepository.findUserByLogin(signUpDto.getLogin());

        if (PersonOpt.isPresent()){
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        Person person = personMapper.signUpToUser(signUpDto);
        person.setRole(Role.USER);
        person.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.getPassword())));
        person.setBirthday(signUpDto.getBirthday());
        Person savedUser = personRepository.save(person);
        return personMapper.toPersonDto(savedUser);
    }

    public PersonDto save(PersonDto personDto) {
        Person existingPerson = personRepository.findUserByLogin(personDto.getLogin())
                .orElseThrow(() -> new AppException("Personne inconnu", HttpStatus.NOT_FOUND));
        existingPerson.setToken(personDto.getToken());
        existingPerson.setRefreshToken(personDto.getRefreshToken());

        Person updatedPerson = personRepository.save(existingPerson);

        return  personMapper.toPersonDto(updatedPerson);
    }

    public PersonDto findByRefreshToken(String refreshToken) {
        Person person = personRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new AppException("Personne avec ce refreshToken inconnu", HttpStatus.NOT_FOUND));
        return personMapper.toPersonDto(person);
    }

    public PersonDto findByLogin(String login) {
        Person person = personRepository.findUserByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return personMapper.toPersonDto(person);
    }

    public void clearTokens(PersonDto personDto) {
        personDto.setToken(null);
        personDto.setRefreshToken(null);
        save(personDto);
    }

    // SERVICE RELATIF AUX GUESTS

    public PersonDto findByEmail(String email) {
        Optional<Person> personOpt = personRepository.findByEmail(email);
        if(personOpt.isPresent()){
            Person person = personOpt.get();
            return personMapper.toPersonDto(person);
        }
        return null;
    }

    public List<PersonDto> findGuestByEvent(Long idEvent) {
        Optional<Event> eventOpt = eventRepository.findById(idEvent);
        if(eventOpt.isPresent()){
            Event event = eventOpt.get();
            List<UserEventRole> userEventRoleList = userEventRoleRepository.findByEvent(event);
            List<PersonDto> personDtos = new ArrayList<>();
            for(UserEventRole userEventRole: userEventRoleList){
                Optional<Person> personOpt = personRepository.findByidPerson(userEventRole.getPerson().getIdPerson());
                if (personOpt.isPresent()){
                    Person person = personOpt.get();
                    PersonDto personDto = personMapper.toPersonDto(person);
                    personDtos.add(personDto);
                }
            }
            return personDtos;
        }
        return null;
    }

    public PersonDto avatar(Long idPerson, String avatar) {
        Optional<Person> personOpt = personRepository.findByidPerson(idPerson);
        if(personOpt.isPresent()){
            Person person = personOpt.get();
            person.setAvatar(avatar);
            personRepository.save(person);
            PersonDto personDto = personMapper.toPersonDto(person);
            return personDto;
        }
        return null;
    }

    public PersonDto updateUser(Long idPerson, PersonDto personeDto) {
        Optional<Person> personOpt = personRepository.findByidPerson(idPerson);
        if(personOpt.isPresent()){
            Person person = personOpt.get();
            // Fusionner les champs de 'user' avec 'existingUser' selon vos besoins
            if (person.getFirstName() != personeDto.getFirstName()) person.setFirstName(personeDto.getFirstName());
            if (person.getLastName() != personeDto.getLastName()) person.setLastName(personeDto.getLastName());
            if (person.getPassword() != "") person.setPassword(passwordEncoder.encode(CharBuffer.wrap(personeDto.getPassword())));
            if (person.getPhone() != personeDto.getPhone()) person.setPhone(personeDto.getPhone());
            if (person.getEmail() != personeDto.getEmail()) person.setEmail(personeDto.getEmail());
            if (person.getBirthday() != null) person.setBirthday(personeDto.getBirthday());
            if (person.getAdresse() != personeDto.getAdresse()) person.setAdresse(personeDto.getAdresse());
            if (person.getVille() != personeDto.getVille()) person.setVille(personeDto.getVille());
            if (person.getCp() != personeDto.getCp()) person.setCp(personeDto.getCp());

            personRepository.save(person);
            return personMapper.toPersonDto(person);
        }
        return null;
    }

    @Transactional
    public void deleteGuestOfEvent(Long idEvent) {
        // Récupérer la liste des UserEventRole où le role est "INVITE" pour un idEvent spécifique
        List<UserEventRole> userEventRoles = userEventRoleRepository.findByRoleAndEvent_IdEvent("INVITE", idEvent);
        List<UserEventRole> userEventRoleCreateur = userEventRoleRepository.findByRoleAndEvent_IdEvent("CREATEUR", idEvent);
        for (UserEventRole userEventRole: userEventRoleCreateur){
            userEventRoleRepository.delete(userEventRole);
        }
        // Filtrer les entités Person qui ont le role "GUEST" parmi les UserEventRole récupérés
        Set<Person> guestPersons = userEventRoles.stream()
                .map(UserEventRole::getPerson)
                .filter(person -> person.getRole().equals(Role.GUEST))
                .collect(Collectors.toSet());

        for(Person person: guestPersons){
            Long idPerson = person.getIdPerson();
            Long idevent = idEvent;
            deletePersonByIdPerson(idPerson, idevent);
        }
    }
}

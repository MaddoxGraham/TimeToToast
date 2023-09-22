package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.DTOs.CredentialsDto;
import com.maddoxgraham.TimeToToast.DTOs.PersonDto;
import com.maddoxgraham.TimeToToast.DTOs.SignUpDto;
import com.maddoxgraham.TimeToToast.Exception.AppException;
import com.maddoxgraham.TimeToToast.Mappers.PersonMapper;
import com.maddoxgraham.TimeToToast.Models.Enums.Role;
import com.maddoxgraham.TimeToToast.Models.Person;
import com.maddoxgraham.TimeToToast.Repository.PersonRepository;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final PersonMapper personMapper;

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



    // SERVICE RELATIF AUX GUESTS
}

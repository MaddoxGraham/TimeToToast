package com.maddoxgraham.TimeToToast.Mappers;

import com.maddoxgraham.TimeToToast.DTOs.PersonDto;
import com.maddoxgraham.TimeToToast.DTOs.SignUpDto;
import com.maddoxgraham.TimeToToast.Models.Enums.Role;
import com.maddoxgraham.TimeToToast.Models.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public PersonDto toPersonDto(Person person) {
        if (person == null) {
            return null;
        }

        PersonDto dto = new PersonDto();
        dto.setIdPerson(person.getIdPerson());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setEmail(person.getEmail());
        dto.setToken(person.getToken());
        dto.setRole(person.getRole() != null ? person.getRole().name() : null);
        dto.setAdresse(person.getAdresse());
        dto.setCp(person.getCp());
        dto.setVille(person.getVille());
        dto.setBirthday(person.getBirthday());
        dto.setAvatar(person.getAvatar());
        dto.setPhone(person.getPhone());
        dto.setLogin(person.getLogin());
        dto.setPassword(person.getPassword());
        dto.setRefreshToken(person.getRefreshToken());
        dto.setIsPresent(person.getIsPresent());

        return dto;
    }

    public Person toEntity(PersonDto dto) {
        if (dto == null) {
            return null;
        }

        Person person = new Person();
        person.setIdPerson(dto.getIdPerson());
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setEmail(dto.getEmail());
        person.setToken(dto.getToken());
        if (dto.getRole() != null) {
            try {
                person.setRole(Role.valueOf(dto.getRole()));
            } catch (IllegalArgumentException e) {
                person.setRole(null);
            }
        }
        person.setAdresse(dto.getAdresse());
        person.setCp(dto.getCp());
        person.setVille(dto.getVille());
        person.setBirthday(dto.getBirthday());
        person.setAvatar(dto.getAvatar());
        person.setPhone(dto.getPhone());
        person.setLogin(dto.getLogin());
        person.setPassword(dto.getPassword());
        person.setRefreshToken(dto.getRefreshToken());
        person.setIsPresent(dto.getIsPresent());

        return person;
    }

    public Person signUpToUser(SignUpDto signUpDto) {
        if (signUpDto == null) {
            return null;
        }

        Person person = new Person();
        person.setFirstName(signUpDto.getFirstName());
        person.setLastName(signUpDto.getLastName());
        person.setAdresse(signUpDto.getAdresse());
        person.setCp(signUpDto.getCp());
        person.setVille(signUpDto.getVille());
        person.setEmail(signUpDto.getEmail());
        person.setPhone(signUpDto.getPhone());
        person.setBirthday(signUpDto.getBirthday());
        person.setLogin(signUpDto.getLogin());

        return person;
    }

}

package com.maddoxgraham.TimeToToast.Repository;

import com.maddoxgraham.TimeToToast.DTOs.PersonDto;
import com.maddoxgraham.TimeToToast.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findUserByLogin(String login);
    Optional<Person> findByidPerson(Long idPerson);
    Optional<Person> findByRefreshToken(String refreshToken);
    Optional<Person> findByEmail(String email);

    void deleteByIdPerson(Long idPerson);

}

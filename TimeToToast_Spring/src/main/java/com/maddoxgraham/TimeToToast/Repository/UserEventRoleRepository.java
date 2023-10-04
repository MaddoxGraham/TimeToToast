package com.maddoxgraham.TimeToToast.Repository;

import com.maddoxgraham.TimeToToast.Models.Event;
import com.maddoxgraham.TimeToToast.Models.Person;
import com.maddoxgraham.TimeToToast.Models.UserEventRole;
import com.maddoxgraham.TimeToToast.Models.UserEventKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEventRoleRepository extends JpaRepository<UserEventRole, UserEventKey> {
    void deleteByUserEventKey(UserEventKey userEventKey);
    Optional<UserEventRole> findByUserEventKey_IdPersonAndUserEventKey_IdEvent(Long idPerson, Long idEvent);

    List<UserEventRole> findByEvent(Event event);

    void deleteByPerson_IdPersonAndEvent_IdEvent(Long idPerson, Long idEvent);

    List<UserEventRole> findByRoleAndEvent_IdEvent(String role, Long idEvent);

    void deleteByPerson_IdPerson(Long idPerson);

}
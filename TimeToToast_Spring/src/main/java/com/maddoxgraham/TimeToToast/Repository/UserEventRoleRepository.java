package com.maddoxgraham.TimeToToast.Repository;

import com.maddoxgraham.TimeToToast.Models.UserEventRole;
import com.maddoxgraham.TimeToToast.Models.UserEventKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEventRoleRepository extends JpaRepository<UserEventRole, UserEventKey> {
    void deleteByUserEventKey(UserEventKey userEventKey);
    Optional<UserEventRole> findByUserEventKey(UserEventKey userEventKey);
}
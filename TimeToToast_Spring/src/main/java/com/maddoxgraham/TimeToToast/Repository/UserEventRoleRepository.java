package com.maddoxgraham.TimeToToast.Repository;

import com.maddoxgraham.TimeToToast.Models.UserEventRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserEventRoleRepository extends JpaRepository<UserEventRole, Long> {
    void deleteUserEventRoleByIdUserEventRole(Long idUserEventRole);
    Optional<UserEventRole> findUserEventRoleByIdUserEventRole(Long idUserEventRole);
}

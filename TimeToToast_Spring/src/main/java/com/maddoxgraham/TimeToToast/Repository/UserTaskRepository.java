package com.maddoxgraham.TimeToToast.Repository;

import com.maddoxgraham.TimeToToast.Models.UserEventKey;
import com.maddoxgraham.TimeToToast.Models.UserEventRole;
import com.maddoxgraham.TimeToToast.Models.UserTask;
import com.maddoxgraham.TimeToToast.Models.UserTaskKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserTaskRepository extends JpaRepository<UserTask, UserTaskKey> {

    void deleteByUserTaskKey(UserTaskKey userTaskKey);
    Optional<UserTask> findByUserTaskKey(UserTaskKey userTaskKey);

}

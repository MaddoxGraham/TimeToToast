package com.maddoxgraham.TimeToToast.Repository;

import com.maddoxgraham.TimeToToast.Models.Task;
import com.maddoxgraham.TimeToToast.Models.UserTask;
import com.maddoxgraham.TimeToToast.Models.UserTaskKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserTaskRepository extends JpaRepository<UserTask, UserTaskKey> {

    void deleteByUserTaskKey(UserTaskKey userTaskKey);
    Optional<UserTask> findByUserTaskKey_IdTaskAndUserTaskKey_IdPerson(Long idTask, Long idPerson);
    List<UserTask> findByUserTaskKey_IdTask(Long idTask);
    List<UserTask> findByUserTaskKey_IdPerson(Long idPerson);

    void deleteByUserTaskKey_IdTask(Long idTask);
    void deleteByUserTaskKey_IdPerson(Long idPerson);
}

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
    Optional<UserTask> findByUserTaskKey(UserTaskKey userTaskKey);
    List<UserTask> findByIdTask(Long idTask);
    List<UserTask> findByIdPerson(Long idPerson);

    List<UserTask> deleteByIdTask(Long idTask);
    List<UserTask> deleteByIdPerson(Long idPerson);

}

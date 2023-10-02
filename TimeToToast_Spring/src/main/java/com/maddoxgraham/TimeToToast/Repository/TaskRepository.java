package com.maddoxgraham.TimeToToast.Repository;

import com.maddoxgraham.TimeToToast.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    void deleteTaskByIdTask(Long idTask);
    Optional<Task> findTaskByIdTask(Long idTask);

    List<Task> findByEvent_IdEvent(Long idEvent);
}


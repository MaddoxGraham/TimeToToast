package com.maddoxgraham.TimeToToast.Repository;

import com.maddoxgraham.TimeToToast.Models.HiddenUserTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HiddenUserTaskRepository extends JpaRepository<HiddenUserTask, Long> {
    void deleteByHiddenUserTaskKey(Long hiddenUserTaskKey);
    Optional<HiddenUserTask> findByHiddenUserTaskKey(Long hiddenUserTaskKey);
//    List<HiddenUserTask> findAllByUserIdUser(Long idUser);
//    List<HiddenUserTask> findAllByGuestIdGuest(Long idGuest);
    void deleteByTaskIdTask(Long idTask); // Nouvelle méthode pour supprimer par idTask
    List<HiddenUserTask> findAllByTaskIdTask(Long idTask); // Nouvelle méthode pour trouver par idTask
}

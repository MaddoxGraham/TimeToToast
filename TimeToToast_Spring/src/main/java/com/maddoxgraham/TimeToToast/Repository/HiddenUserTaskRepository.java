package com.maddoxgraham.TimeToToast.Repository;

import com.maddoxgraham.TimeToToast.Models.HiddenUserTask;
import com.maddoxgraham.TimeToToast.Models.HiddenUserTaskKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HiddenUserTaskRepository extends JpaRepository<HiddenUserTask, HiddenUserTaskKey> {
    void deleteByHiddenUserTaskKey(HiddenUserTaskKey hiddenUserTaskKey);
    Optional<HiddenUserTask> findByHiddenUserTaskKey(HiddenUserTaskKey hiddenUserTaskKey);
    List<HiddenUserTask> findAllByUserIdUser(Long idUser);
}

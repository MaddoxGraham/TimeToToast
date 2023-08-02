package com.maddoxgraham.TimeToToast.Repository;

import com.maddoxgraham.TimeToToast.Models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    void deleteEventByIdEvent(Long idEvent);
    Optional<Event> findEventByIdEvent(Long idEvent);
}

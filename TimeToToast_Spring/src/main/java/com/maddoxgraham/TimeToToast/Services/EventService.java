package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.TimeToToastApplication;
import com.maddoxgraham.TimeToToast.Exception.UserNotFoundException;
import com.maddoxgraham.TimeToToast.Models.Event;
import com.maddoxgraham.TimeToToast.Repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event addEvent(Event event){
        return eventRepository.save(event);
    }

    public List<Event> findAllEvents(){
        return eventRepository.findAll();
    }

    public Event updateEvent(Event event){
        return eventRepository.save(event);
    }

    public Event findEventByIdEvent(Long idEvent){
        return eventRepository.findEventByIdEvent(idEvent).orElseThrow(() -> new UserNotFoundException("Event n° " + idEvent + " was not found"));
    }
    @Transactional
    public void deleteEvent(Long idEvent){
        eventRepository.deleteById(idEvent);
    }
}

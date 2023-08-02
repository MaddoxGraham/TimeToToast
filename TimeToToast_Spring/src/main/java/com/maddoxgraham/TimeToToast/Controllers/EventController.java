package com.maddoxgraham.TimeToToast.Controllers;

import com.maddoxgraham.TimeToToast.Models.Event;
import com.maddoxgraham.TimeToToast.Services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAllEvent() {
        List<Event> events = eventService.findAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/find/{idEvent}")
     public ResponseEntity<Event> getEventById(@PathVariable("idEvent") Long idEvent) {
        Event event = eventService.findEventByIdEvent(idEvent);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PostMapping("/add")
     public ResponseEntity<Event> addEvent(@RequestBody Event event){
        Event newEvent = eventService.addEvent(event);
        return new ResponseEntity<>(newEvent, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Event> updateEvent(@RequestBody Event event){
        Event updateEvent = eventService.updateEvent(event);
        return new ResponseEntity<>(updateEvent, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idEvent}")
    public ResponseEntity<?> deleteEvent(@PathVariable("idEvent") Long idEvent){
        eventService.deleteEvent(idEvent);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}



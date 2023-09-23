package com.maddoxgraham.TimeToToast.Controllers;

import com.maddoxgraham.TimeToToast.DTOs.EmailDataDto;
import com.maddoxgraham.TimeToToast.Models.Event;
import com.maddoxgraham.TimeToToast.Models.Person;
import com.maddoxgraham.TimeToToast.Services.EmailService;
import com.maddoxgraham.TimeToToast.Services.EventService;
import com.maddoxgraham.TimeToToast.Services.PersonService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/event")
@AllArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EmailService emailService;
    private final PersonService personService;


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

    @PutMapping("/update/{idEvent}/{moduleName}")
    public ResponseEntity<Event> updateModuleBoolean(@PathVariable("idEvent") Long idEvent,@PathVariable("moduleName") String moduleName ){
        Event event = eventService.findEventByIdEvent(idEvent);
        if("task".equals(moduleName)){
            event.setTaskModuleActive(!event.isTaskModuleActive());
        }
        else if ("gift".equals(moduleName)) {
             event.setGiftModuleActive(!event.isGiftModuleActive());
        }
        else if ("photo".equals(moduleName)) {
            event.setPhotoModuleActive(!event.isPhotoModuleActive());
        }
        eventService.updateEvent(event);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idEvent}")
    public ResponseEntity<?> deleteEvent(@PathVariable("idEvent") Long idEvent){
        eventService.deleteEvent(idEvent);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @PostMapping("/sendHTMLEmail")
    public ResponseEntity sendHTMLEmail(@RequestBody EmailDataDto emailDataDto) throws MessagingException {
        Person person = personService.findPersonByIdPerson(emailDataDto.getIdPerson());
        Event event = eventService.findEventByIdEvent(emailDataDto.getIdEvent());
        try {
            emailService.sendHtmlEmail(emailDataDto.getTo(),person, event);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}



package com.maddoxgraham.TimeToToast.Controllers;

import com.maddoxgraham.TimeToToast.DTOs.EmailDataDto;
import com.maddoxgraham.TimeToToast.Models.EmailData;
import com.maddoxgraham.TimeToToast.Models.Event;
import com.maddoxgraham.TimeToToast.Models.User;
import com.maddoxgraham.TimeToToast.Repository.UserRepository;
import com.maddoxgraham.TimeToToast.Services.EmailService;
import com.maddoxgraham.TimeToToast.Services.EventService;
import com.maddoxgraham.TimeToToast.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;
    private final EmailService emailService;
    private final UserService userService;

    public EventController(EventService eventService,EmailService emailService,UserService userService ) {
        this.eventService = eventService;
        this.emailService = emailService;
        this.userService = userService;
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

//    @PostMapping("/sendEmail")
//    public String sendTestEmail(@RequestBody EmailDataDto emailDataDto) {
//        emailService.sendingMail(emailDataDto.getTo(), emailDataDto.getSubject(), emailDataDto.getBody());
//        return "Email sent successfully!";
//    }

    @PostMapping("/sendHTMLEmail")
    public String sendHTMLEmail(@RequestBody EmailDataDto emailDataDto) {
        User user = userService.findUserByIdUser(emailDataDto.getIdUser());
        Event event = eventService.findEventByIdEvent(emailDataDto.getIdEvent());

        emailService.sendHtmlEmail(emailDataDto.getTo(),user, event);

        return "Email sent successfully!";
    }

}



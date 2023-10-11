package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.Config.UserAuthProvider;
import com.maddoxgraham.TimeToToast.DTOs.UserEventRoleDto;
import com.maddoxgraham.TimeToToast.Models.Enums.Role;
import com.maddoxgraham.TimeToToast.Models.Event;
import com.maddoxgraham.TimeToToast.Models.Person;
import com.maddoxgraham.TimeToToast.Models.UserEventKey;
import com.maddoxgraham.TimeToToast.Models.UserEventRole;
import com.maddoxgraham.TimeToToast.Repository.PersonRepository;
import com.maddoxgraham.TimeToToast.Repository.UserEventRoleRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    private String[] to;
    private String subject;
    private String body;
    private UserAuthProvider userAuthProvider;
    private PersonRepository personRepository;
    private UserEventRoleService userEventRoleService;
    private UserEventRoleRepository userEventRoleRepository;

    public EmailService (UserAuthProvider userAuthProvider, PersonRepository personRepository, UserEventRoleService userEventRoleService, UserEventRoleRepository userEventRoleRepository) {
        this.userAuthProvider = userAuthProvider;
        this.personRepository = personRepository;
        this.userEventRoleService = userEventRoleService;
        this.userEventRoleRepository = userEventRoleRepository;
    }

    public void sendingMail(String[] to, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public void sendHtmlEmail(String[] toAddresses, Person host, Event event) throws MessagingException {
        //Host : celui qui créer et invite à l'événement.
        //Guest : celui qui est invité à l'événement.

        for (String to : toAddresses) {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            // Enregistrement du candidat
            Optional<Person> personOpt = personRepository.findByEmail(to);
            Person guest = new Person();
            if(personOpt.isPresent()){
                guest = personOpt.get();
                UserEventKey userEventKey = new UserEventKey();
                userEventKey.setIdPerson(guest.getIdPerson());
                userEventKey.setIdEvent(event.getIdEvent());
                Optional<UserEventRole> userEventRoleOpt = userEventRoleRepository.findByUserEventKey_IdPersonAndUserEventKey_IdEvent(guest.getIdPerson(), event.getIdEvent());
                if(userEventRoleOpt.isEmpty()){
                    UserEventRoleDto userEventRoleDto = new UserEventRoleDto();
                    userEventRoleDto.setIdEvent(event.getIdEvent());
                    userEventRoleDto.setIdPerson(guest.getIdPerson());
                    userEventRoleDto.setRole("INVITE");
                    userEventRoleService.addUserEventRole(userEventRoleDto);
                }
            } else {
                guest.setEmail(to);
                guest.setIsPresent(false);
                guest.setRole(Role.GUEST);
                guest.setToken(userAuthProvider.createGuestToken(guest, event));
                personRepository.save(guest);
                UserEventRoleDto userEventRoleDto = new UserEventRoleDto();
                userEventRoleDto.setIdEvent(event.getIdEvent());
                userEventRoleDto.setIdPerson(guest.getIdPerson());
                userEventRoleDto.setRole("INVITE");
                userEventRoleService.addUserEventRole(userEventRoleDto);
            }
          //génération d'un token et d'un lien associé
            helper.setTo(to);
            helper.setSubject(host.getLastName() + ' ' + host.getFirstName() + " vous invite à l'événement : " + event.getTitle());

            String htmlContent = " <h2 style=\"font-family: 'Phudu', cursive; font-size: 1.5rem; font-variant: small-caps; letter-spacing: 2px; color: #a57b45; margin-bottom: 5%;\">" + host.getLastName() + ' ' + host.getFirstName() + " vous invite à l'événement : " + event.getTitle() + "</h2>"
                 + "<p> Le" + event.getEventDate() + " à " + event.getVille() +  "</p>"
                   + "<h4 style=\"font-size: 14px; font-variant: small-caps; letter-spacing: 2px; margin-top: 5%;\">Cliquez sur le lien pour participer.</h4>"
                    + "<a href='http://localhost:4200/guest/newGuest/" + guest.getToken() + "'>Participer</a>";

            helper.setText(htmlContent, true);

            mailSender.send(message);
        }
    }


}

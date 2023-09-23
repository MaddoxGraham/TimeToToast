package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.Config.UserAuthProvider;
import com.maddoxgraham.TimeToToast.Models.Enums.Role;
import com.maddoxgraham.TimeToToast.Models.Event;
import com.maddoxgraham.TimeToToast.Models.Person;
import com.maddoxgraham.TimeToToast.Repository.PersonRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    private String[] to;
    private String subject;
    private String body;
    private UserAuthProvider userAuthProvider;
    private PersonRepository personRepository;

    public EmailService (UserAuthProvider userAuthProvider, PersonRepository personRepository ) {
        this.userAuthProvider = userAuthProvider;
        this.personRepository = personRepository;
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
            Person guest = new Person();
            guest.setEmail(to);
            guest.setIsPresent(false);
            guest.setRole(Role.GUEST);
            guest.setEvent(event);
            guest.setToken(userAuthProvider.createGuestToken(guest, event));
            personRepository.save(guest);

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

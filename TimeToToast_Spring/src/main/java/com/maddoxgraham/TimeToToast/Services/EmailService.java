package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.Config.UserAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    private String[] to;
    private String subject;
    private String body;
    //private UserAuthProvider userAuthProvider;

    public EmailService (UserAuthProvider userAuthProvider
                         //GuestRepository guestRepository
                         ) {
        //this.userAuthProvider = userAuthProvider;
        //this.guestRepository = guestRepository;
    }

    public void sendingMail(String[] to, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

//    public void sendHtmlEmail(String[] toAddresses, User user, Event event) throws MessagingException {
//        for (String to : toAddresses) {
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            // Enregistrement du candidat
//            Guest guest = new Guest();
//            guest.setEmail(to);
//            guest.setIsPresent(false);
//            guest.setRole(Role.GUEST);
//            guest.setEvent(event);
//            guest.setToken(userAuthProvider.createGuestToken(guest, event));
//            guestRepository.save(guest);
//
//          //génération d'un token et d'un lien associé
//
//            helper.setTo(to);
//            helper.setSubject(user.getLastName() + ' ' + user.getFirstName() + " vous invite à l'événement : " + event.getTitle());
//
//            String htmlContent = " <h2 style=\"font-family: 'Phudu', cursive; font-size: 1.5rem; font-variant: small-caps; letter-spacing: 2px; color: #a57b45; margin-bottom: 5%;\">" + user.getLastName() + ' ' + user.getFirstName() + " vous invite à l'événement : " + event.getTitle() + "</h2>"
//                 + "<p> Le" + event.getEventDate() + " à " + event.getVille() +  "</p>"
//                   + "<h4 style=\"font-size: 14px; font-variant: small-caps; letter-spacing: 2px; margin-top: 5%;\">Cliquez sur le lien pour participer.</h4>"
//                    + "<a href='http://localhost:4200/guest/newGuest/" + guest.getToken() + "'>Participer</a>";
//
//            helper.setText(htmlContent, true);
//
//            mailSender.send(message);
//        }
//    }


}

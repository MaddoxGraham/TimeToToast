package com.maddoxgraham.TimeToToast.Services;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
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

    public void sendingMail(String[] to, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public void sendHtmlEmail(String[] toAddresses, String eventName, String creatorName) throws MessagingException {
        for (String to : toAddresses) {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(creatorName + " vous invite à l'événement : " + eventName);

            String htmlContent = "<h1>" + creatorName + " vous invite à l'événement : " + eventName + "</h1>"
                    + "<p>Cliquez sur le lien pour participer.</p>"
                    + "<a href='ton_lien_ici'>Participer</a>";

            helper.setText(htmlContent, true);

            mailSender.send(message);
        }
    }
}

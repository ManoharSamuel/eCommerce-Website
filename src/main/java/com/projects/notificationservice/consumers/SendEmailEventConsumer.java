package com.projects.notificationservice.consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projects.notificationservice.dtos.SendEmailEventDTO;
import com.projects.notificationservice.utils.EmailUtil;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Service
public class SendEmailEventConsumer {
    private ObjectMapper objectMapper;
    
    public SendEmailEventConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics="sendEmail",groupId = "GroupOne")
    public void handleSendEmailEvent(String message) throws JsonProcessingException {
        SendEmailEventDTO sendEmailEventDTO = objectMapper.readValue(message, SendEmailEventDTO.class);
        
        String fromEmail = sendEmailEventDTO.getFrom();
        String toEmail = sendEmailEventDTO.getTo();
        String subject = sendEmailEventDTO.getSubject();
        String body = sendEmailEventDTO.getEmailBody();

        //final String password = "mypassword"; // correct password for gmail id
        
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        props.put("enable.partition.eof", "false");

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, "wribovzgkvtzaycw");
            }
        };
        Session session = Session.getInstance(props, auth);
        
        EmailUtil.sendEmail(session, toEmail,subject, body);
    }
}

package com.bookstore.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bookstore.dto.MailBody;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendmessage(MailBody mailBody){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailBody.to());
        message.setSubject(mailBody.subject());
        message.setText(mailBody.text());    
        javaMailSender.send(message);
    }
}
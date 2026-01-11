package com.ecohabit.eco_habit_nlp_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendResetMail(String to, String token) {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(to);
        mail.setSubject("Eco Habit - Password Reset");
        mail.setText(
            "Reset your password using the link below:\n\n" +
            "http://localhost:3000/reset-password?token=" + token
        );

        mailSender.send(mail);
    }
}

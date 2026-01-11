package com.ecohabit.eco_habit_nlp_system.service;

import com.ecohabit.eco_habit_nlp_system.model.PasswordResetToken;
import com.ecohabit.eco_habit_nlp_system.model.User;
import com.ecohabit.eco_habit_nlp_system.repository.PasswordResetRepository;
import com.ecohabit.eco_habit_nlp_system.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordResetRepository tokenRepo;

    @Autowired
    private JavaMailSender mailSender;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    // 🔐 Strong password rule
    private final Pattern strongPassword =
            Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$");

    // ======================
    // REGISTER
    // ======================
    public User register(String name, String email, String password) {

        if (userRepo.existsByEmail(email)) {
            throw new RuntimeException("❌ Email already registered");
        }

        if (!strongPassword.matcher(password).matches()) {
            throw new RuntimeException(
                    "❌ Password must contain uppercase, lowercase, number & 8+ chars"
            );
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        return userRepo.save(user);
    }

    // ======================
    // LOGIN
    // ======================
    public User login(String email, String password) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("❌ User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("❌ Invalid password");
        }

        return user;
    }

    // ======================
    // FORGOT PASSWORD
    // ======================
    public void forgotPassword(String email) {

        userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("❌ Email not registered"));

        String token = UUID.randomUUID().toString();

        PasswordResetToken prt = new PasswordResetToken();
        prt.setEmail(email);
        prt.setToken(token);
        prt.setExpiry(LocalDateTime.now().plusHours(24));

        tokenRepo.save(prt);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Eco Habit - Password Reset");
        message.setText(
                "Reset your password using this link:\n" +
                "http://localhost:3000/reset-password?token=" + token
        );

        mailSender.send(message);
    }

    // ======================
    // RESET PASSWORD
    // ======================
    public void resetPassword(String token, String newPassword) {

        PasswordResetToken prt = tokenRepo.findByToken(token)
                .orElseThrow(() -> new RuntimeException("❌ Invalid token"));

        if (prt.getExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("❌ Token expired");
        }

        if (!strongPassword.matcher(newPassword).matches()) {
            throw new RuntimeException(
                    "❌ Password must contain uppercase, lowercase & number"
            );
        }

        User user = userRepo.findByEmail(prt.getEmail())
                .orElseThrow(() -> new RuntimeException("❌ User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);

        // 🧹 Clear token after use
        tokenRepo.delete(prt);
    }
}

package com.ecohabit.eco_habit_nlp_system.controller;

import com.ecohabit.eco_habit_nlp_system.model.User;
import com.ecohabit.eco_habit_nlp_system.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "https://ecohabit-nlp.netlify.app/")
public class AuthController {

    @Autowired
    private UserService userService;

    // ======================
    // REGISTER
    // ======================
    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest req) {
        return userService.register(
                req.name(),
                req.email(),
                req.password()
        );
    }

    // ======================
    // LOGIN
    // ======================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        User user = userService.login(req.email(), req.password());

        return ResponseEntity.ok(
            Map.of(
                "id", user.getId(),
                "name", user.getName(),
                "email", user.getEmail()
            )
        );
    }


    // ======================
    // FORGOT PASSWORD
    // ======================
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotRequest req) {
        userService.forgotPassword(req.email());
        return ResponseEntity.ok("✅ Check your email for password reset link!");
    }

    // ======================
    // RESET PASSWORD
    // ======================
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetRequest req) {
        userService.resetPassword(
                req.token(),
                req.newPassword()
        );
        return ResponseEntity.ok("✅ Password reset successful!");
    }

    // ======================
    // DTOs
    // ======================
    public record RegisterRequest(String name, String email, String password) {}
    public record LoginRequest(String email, String password) {}
    public record ForgotRequest(String email) {}
    public record ResetRequest(String token, String newPassword) {}
}


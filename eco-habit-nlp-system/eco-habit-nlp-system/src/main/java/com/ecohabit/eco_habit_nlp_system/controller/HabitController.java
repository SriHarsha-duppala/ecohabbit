package com.ecohabit.eco_habit_nlp_system.controller;

import com.ecohabit.eco_habit_nlp_system.dto.HabitRequest;
import com.ecohabit.eco_habit_nlp_system.model.Habit;
import com.ecohabit.eco_habit_nlp_system.service.HabitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/habits")
@CrossOrigin(origins = "http://localhost:3000")
public class HabitController {

    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<?> analyzeHabit(@RequestBody HabitRequest request) {

        // ✅ VALIDATION (prevents 500 error)
        if (request.getText() == null || request.getText().trim().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Habit text must not be empty");
        }

        if (request.getUserId() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("User ID must not be null");
        }

        Habit habit = habitService.analyzeHabit(
                request.getText(),
                request.getUserId()
        );

        return ResponseEntity.ok(habit);
    }
}

package com.ecohabit.eco_habit_nlp_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

        @NotBlank
        String name,     // ✅ NEW

        @Email
        String email,

        @Size(min = 8)
        String password
) {}

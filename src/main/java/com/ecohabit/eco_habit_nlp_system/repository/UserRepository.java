package com.ecohabit.eco_habit_nlp_system.repository;

import com.ecohabit.eco_habit_nlp_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}

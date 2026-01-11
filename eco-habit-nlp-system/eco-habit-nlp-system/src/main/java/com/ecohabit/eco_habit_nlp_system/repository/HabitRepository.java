package com.ecohabit.eco_habit_nlp_system.repository;

import com.ecohabit.eco_habit_nlp_system.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {

    // ✅ MONTHLY STATS
	@Query("""
		    SELECT 
		        COUNT(h.id),
		        AVG(h.ecoScore),
		        SUM(h.carbonFootprint)
		    FROM Habit h
		    WHERE h.userId = :userId
		      AND h.createdAt BETWEEN :start AND :end
		""")
	List<Object[]> getMonthlyStats(
	        @Param("userId") Long userId,
	        @Param("start") LocalDateTime start,
	        @Param("end") LocalDateTime end
	);



    // ✅ DAILY ECO SCORE (RAW DATA)
    @Query("""
        SELECT 
            DAY(h.createdAt),
            AVG(h.ecoScore)
        FROM Habit h
        WHERE h.userId = :userId
          AND h.createdAt BETWEEN :start AND :end
        GROUP BY DAY(h.createdAt)
        ORDER BY DAY(h.createdAt)
    """)
    List<Object[]> getDailyEcoScoreTrend(
            @Param("userId") Long userId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    List<Habit> findByUserIdAndCreatedAtBetween(
            Long userId,
            LocalDateTime start,
            LocalDateTime end
    );
}

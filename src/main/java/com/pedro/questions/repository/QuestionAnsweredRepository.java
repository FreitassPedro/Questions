package com.pedro.questions.repository;

import com.pedro.questions.entity.QuestionAnswered;
import com.pedro.questions.entity.UserStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionAnsweredRepository extends JpaRepository<QuestionAnswered, Integer> {
    boolean existsByUserStatisticsAndQuestionId(UserStatistics userStatistics, int questionId);
    Optional<QuestionAnswered> findByQuestionId(int id);
}

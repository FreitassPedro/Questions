package com.pedro.questions.repository;

import com.pedro.questions.entity.UserStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatisticRepository extends JpaRepository<UserStatistics, Integer> {
}

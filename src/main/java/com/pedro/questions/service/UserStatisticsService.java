package com.pedro.questions.service;

import com.pedro.questions.entity.Question;
import com.pedro.questions.repository.UserStatisticRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserStatisticsService {

    private final UserStatisticRepository userStatisticRepository;

    public void processData(Question question, boolean isCorrect) {
        if (isCorrect){}


    }
}

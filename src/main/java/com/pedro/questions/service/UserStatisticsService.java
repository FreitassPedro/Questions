package com.pedro.questions.service;

import com.pedro.questions.entity.Question;
import com.pedro.questions.entity.QuestionAnswered;
import com.pedro.questions.entity.UserStatistics;
import com.pedro.questions.entity.Users;
import com.pedro.questions.repository.QuestionAnsweredRepository;
import com.pedro.questions.repository.UserStatisticRepository;
import com.pedro.questions.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class UserStatisticsService {

    private final UserStatisticRepository userStatisticRepository;
    private final UsersRepository usersRepository;
    private final QuestionAnsweredRepository questionAnsweredRepository;

    public void answerIsIncorrect() {}

    public void processAnswer(Question question, UserStatistics userStatistics) {
        boolean isCorrect = question.getRespostaCorreta() == question.getRespostaUsuario();

        QuestionAnswered questionAlreadyAnswered = findQuestionIfWasAnswered(question);

        if (Objects.isNull(questionAlreadyAnswered)) {
            userStatistics.addNewAnswer(question.getId(), isCorrect);
        } else {
            userStatistics.updateAnswer(questionAlreadyAnswered, isCorrect);
        }
        userStatisticRepository.saveAndFlush(userStatistics);
    }

    private QuestionAnswered findQuestionIfWasAnswered(Question question) {
        return questionAnsweredRepository.findByQuestionId(question.getId())
                .orElse(null);
    }

    public Users getCurrentUser(Authentication authentication) {
        return usersRepository.findByEmail(authentication.getName()).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );
    }

    public UserStatistics findByUserId(Users user) {
        UserStatistics userStatistics = userStatisticRepository.findById(user.getId()).orElse(new UserStatistics());
        userStatistics.setUsers(user);
        return userStatistics;
    }
}

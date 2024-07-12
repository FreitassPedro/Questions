package com.pedro.questions.service;

import com.pedro.questions.entity.*;
import com.pedro.questions.repository.QuestionAnsweredRepository;
import com.pedro.questions.repository.UserStatisticRepository;
import com.pedro.questions.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class UserStatisticsService {

    private final UserStatisticRepository userStatisticRepository;
    private final UsersRepository usersRepository;
    private final QuestionAnsweredRepository questionAnsweredRepository;

    public void processAnswer(Question question, UserStatistics userStatistics) {
        boolean isCorrect = question.getRespostaCorreta() == question.getRespostaUsuario();

        QuestionAnswered questionAnswered = findQuestionIfWasAnswered(question);

        if (questionAnswered.getId() == null) {
            questionAnswered.setCorrect(isCorrect);
            userStatistics.addNewAnswer(questionAnswered, question.getSubject());
        } else {
            userStatistics.updateAnswer(questionAnswered, isCorrect);
        }

        userStatistics.updateSubjectStatistics(question.getSubject(), isCorrect);
        System.out.println("Rate perSubject:  " + userStatistics.getSubjectStatistics().toString());
        userStatisticRepository.saveAndFlush(userStatistics);
    }

    private QuestionAnswered findQuestionIfWasAnswered(Question question) {
        return questionAnsweredRepository.findByQuestionId(question.getId())
                .orElse(new QuestionAnswered());
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

package com.pedro.questions.service;

import com.pedro.questions.entity.Question;
import com.pedro.questions.entity.UserStatistics;
import com.pedro.questions.entity.Users;
import com.pedro.questions.repository.UserStatisticRepository;
import com.pedro.questions.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserStatisticsService {

    private final UserStatisticRepository userStatisticRepository;
    private final UsersRepository usersRepository;

    public UserStatistics findById(int id) {
        UserStatistics userStatistics = userStatisticRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("UserStatistics not found")
        );
        return userStatistics;
    }


    public void processData(Question question, Users user) {
        if (question.getRespostaUsuario() == question.getRespostaCorreta()){
            UserStatistics userStatistics = findById(user.getId());

            boolean isCorrect = question.getRespostaCorreta() == question.getRespostaUsuario();
            userStatistics.addAnswer(question.getId(), isCorrect);
            userStatisticRepository.save(userStatistics);
            log.info("Verificando resposta... " + getClass().getSimpleName() + " | " + userStatistics);
        }


    }

    public Users getCurrentUser(Authentication authentication) {
        return usersRepository.findByEmail(authentication.getName()).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );
    }
}

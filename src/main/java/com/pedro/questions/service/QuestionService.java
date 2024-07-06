package com.pedro.questions.service;

import com.pedro.questions.entity.Question;
import com.pedro.questions.entity.QuestionAnswered;
import com.pedro.questions.repository.QuestionAnsweredRepository;
import com.pedro.questions.repository.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;


    public void save(Question question) {
        questionRepository.save(question);
    }

    public Question findById(int id) {
        return questionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User nao eoncontrado")
        );

    }
}

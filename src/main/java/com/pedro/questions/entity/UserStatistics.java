package com.pedro.questions.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table()
public class UserStatistics implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private Users users;

    private int totalAnswered;
    private int totalCorrect;
    private int totalWrong;

    public int getTotalAnswered() {
        return answered.size();
    }

    @OneToMany(mappedBy = "userStatistics", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<QuestionAnswered> answered;


    public void addAnswer(int questionId, boolean isCorrect) {
        Optional<QuestionAnswered> existingAnswer = answered.stream()
                .filter(qa -> qa.getQuestionId() == questionId)
                .findFirst();

        if (existingAnswer.isPresent()) {
            // Questão já respondida, atualizar se necessário
            QuestionAnswered answer = existingAnswer.get();
            if (answer.isCorrect() != isCorrect) {
                answer.setCorrect(isCorrect);
                // Atualizar contadores (totalCorrect, totalWrong) conforme necessário
            }
        } else {
            // Questão não respondida, adicionar nova resposta
            answered.add(new QuestionAnswered(0, this, questionId, isCorrect));
            totalAnswered++;
            if (isCorrect) totalCorrect++;
            else totalWrong++;
        }
    }
}

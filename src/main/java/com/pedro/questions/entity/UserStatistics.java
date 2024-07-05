package com.pedro.questions.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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

    @OneToMany
    @MapsId
    @JoinColumn(name = "questions_id")
    private Map<Integer, Boolean> answered;

    public void addAnswer(int questionId, boolean isCorrect) {
        if (answered == null) answered = new HashMap<>();

        if (answered.containsKey(questionId)) {
            if (answered.get(questionId)) totalCorrect--;
            else totalWrong--;
            answered.replace(questionId, isCorrect);

        } else {
            answered.put(questionId, isCorrect);
            totalAnswered++;
        }
        if (isCorrect) totalCorrect++;
        else totalWrong++;
    }
}

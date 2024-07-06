package com.pedro.questions.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


    public void updateAnswer(QuestionAnswered newAnswer, boolean isCorrect) {
        if (newAnswer.isCorrect() && !(isCorrect)) {
            totalWrong++;
            totalCorrect--;
        } else if (!(newAnswer.isCorrect()) && isCorrect) {
            totalWrong--;
            totalCorrect++;
        }

        Optional<QuestionAnswered> oldAnswer = answered.stream().filter(
                qa -> qa.getQuestionId() == newAnswer.getQuestionId()
        ).findFirst();
        if (oldAnswer.isPresent()) {
            answered.remove(oldAnswer);
            answered.add(newAnswer);
        }

    }

    public void addNewAnswer(int id, boolean isCorrect) {
        if (Objects.isNull(answered)) answered = new HashSet<>();

        QuestionAnswered questionAnswered = new QuestionAnswered();
        questionAnswered.setQuestionId(id);
        questionAnswered.setCorrect(isCorrect);

        answered.add(questionAnswered);
        if (isCorrect) totalCorrect++;
        else totalWrong++;

        this.totalAnswered++;
    }


}

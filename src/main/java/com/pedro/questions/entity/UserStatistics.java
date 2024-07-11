package com.pedro.questions.entity;

import com.pedro.questions.entity.enums.Materia;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table()
public class UserStatistics implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = {CascadeType.MERGE})
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


    @Transient // Não persistir o Map no banco de dados
    private Map<Materia, Double> taxaAcertosPorMateria;


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

        updateMateria(newAnswer);

    }

    private void updateMateria(QuestionAnswered questionAnswered) {
        if (Objects.isNull(taxaAcertosPorMateria)) taxaAcertosPorMateria = new HashMap<>();
        Materia materia = questionAnswered.getMateria();
        if (taxaAcertosPorMateria.containsKey(questionAnswered.getMateria())) {
            taxaAcertosPorMateria.replace(
                    materia, taxaAcertosPorMateria.get(materia) + 1);
        } else {
            taxaAcertosPorMateria.put(materia, 1.0);
        }
    }

    public void addNewAnswer(QuestionAnswered questionAnswered, boolean isCorrect) {
        if (Objects.isNull(answered)) answered = new HashSet<>();

        questionAnswered.setCorrect(isCorrect);;
        updateMateria(questionAnswered);

        answered.add(questionAnswered);
        if (isCorrect) totalCorrect++;
        else totalWrong++;

        this.totalAnswered++;
    }


}

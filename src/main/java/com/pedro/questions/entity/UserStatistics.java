package com.pedro.questions.entity;

import com.pedro.questions.entity.enums.Subject;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    @ElementCollection
    @CollectionTable(name = "subject_statistics", joinColumns = @JoinColumn(name = "user_statistics_id"))
    @MapKeyColumn(name = "subject")
    @Column(name = "statistics")
    @Fetch(FetchMode.JOIN)
    private Map<Subject, SubjectStatistics> subjectStatistics;


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

    public void addNewAnswer(QuestionAnswered questionAnswered, Subject subject) {
        if (Objects.isNull(answered)) answered = new HashSet<>();

        answered.add(questionAnswered);

        if (questionAnswered.isCorrect()) totalCorrect++;
        else totalWrong++;

        this.totalAnswered++;
    }

    public void updateSubjectStatistics(Subject subject, boolean isCorrect) {
        if (Objects.isNull(subjectStatistics)) subjectStatistics = new HashMap<>();

        if (subjectStatistics.containsKey(subject)) {
            SubjectStatistics retrievedRate = subjectStatistics.get(subject);
            retrievedRate.addAnswer(isCorrect);
            subjectStatistics.replace(subject, retrievedRate);
        } else {
            SubjectStatistics newSubjectStatistics = new SubjectStatistics();
            newSubjectStatistics.addAnswer(isCorrect);
            subjectStatistics.put(subject, newSubjectStatistics);
        }
    }

}

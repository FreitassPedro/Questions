package com.pedro.questions.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "question_answers")
public class QuestionAnswered {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_statistics_id")
    private UserStatistics userStatistics;

    @Column(name = "question_id")
    private int questionId;

    @Column(name = "is_correct")
    private boolean isCorrect;
}

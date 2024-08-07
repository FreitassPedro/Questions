package com.pedro.questions.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Table()
public class QuestionAnswered {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_statistics_id")
    private UserStatistics userStatistics;

    @Column(name = "question_id")
    private int questionId;

    @Column(name = "is_correct")
    private boolean isCorrect;
}

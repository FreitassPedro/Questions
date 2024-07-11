package com.pedro.questions.entity;

import com.pedro.questions.entity.enums.Materia;
import com.pedro.questions.entity.enums.Topico;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "question_answers")
public class QuestionAnswered {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_statistics_id")
    private UserStatistics userStatistics;

    @Enumerated(EnumType.STRING) // Supondo que Materia seja um enum
    private Materia materia;

    @Enumerated(EnumType.STRING)
    private Topico topico;

    @Column(name = "question_id")
    private int questionId;

    @Column(name = "is_correct")
    private boolean isCorrect;
}

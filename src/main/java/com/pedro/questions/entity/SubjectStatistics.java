package com.pedro.questions.entity;

import com.pedro.questions.entity.enums.Subject;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode @ToString @Builder
public class SubjectStatistics {


    @Enumerated(EnumType.STRING)
    @MapsId("subject")
    private Subject subject;

    private int totalAnswered;
    private int totalCorrect;


    protected void addAnswer(boolean isCorrect) {
        totalAnswered++;
        if (isCorrect) totalCorrect++;
    }
}



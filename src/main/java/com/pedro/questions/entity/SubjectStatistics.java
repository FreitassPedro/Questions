package com.pedro.questions.entity;

import com.pedro.questions.entity.enums.Subject;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode @ToString @Builder
@Embeddable
public class SubjectStatistics {
    private int totalAnswered;
    private int totalCorrect;
    private int totalWrong;

    protected void addAnswer(boolean isCorrect) {
        totalAnswered++;
        if (isCorrect) totalCorrect++;
    }


}



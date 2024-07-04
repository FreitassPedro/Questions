package com.pedro.questions.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

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

    private int totalAnswer;
    private int totalCorrect;
    private int totalWrong;
}

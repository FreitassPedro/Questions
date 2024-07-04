package com.pedro.questions.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
@Table(name = "user_profile")
public class UserProfile implements Serializable {

    @Id
    private int userAccountId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_account_id")
    private Users userId;

    private String firstName;
    private String lastName;

    public UserProfile(Users user) {
        this.userId = user;
    }
}

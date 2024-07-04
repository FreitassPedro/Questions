package com.pedro.questions.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users_type")
@Setter @Getter @ToString @NoArgsConstructor @AllArgsConstructor
public class UsersType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userTypeId;

    private String userTypeName;

    @OneToMany(targetEntity = Users.class, mappedBy = "userTypeId",  cascade = CascadeType.ALL)
    private List<Users> users;

}

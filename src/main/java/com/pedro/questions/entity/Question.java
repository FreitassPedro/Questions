package com.pedro.questions.entity;

import com.pedro.questions.entity.enums.Materia;
import com.pedro.questions.entity.enums.Subject;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashMap;


@Entity
@Getter @Setter @ToString @EqualsAndHashCode @AllArgsConstructor @NoArgsConstructor
@Table(name = "question")
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private Materia materia;

    @Enumerated(EnumType.STRING)
    private Subject subject;

    @Lob
    @Column(name = "enunciado")
    private String enunciado;

    @Column(name = "alternativas")
    private HashMap<Character, String> alternativas;
    @Column(name = "resposta_correta")
    private Character respostaCorreta;

    @Column(name = "resposta")
    private Character respostaUsuario;

    public void addAlternativas(Character letra, String textoOpcao) {
        if (alternativas == null) alternativas = new HashMap<>();

        alternativas.put(letra, textoOpcao);
    }

}


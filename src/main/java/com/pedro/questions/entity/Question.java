package com.pedro.questions.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashMap;

@Entity
@Getter @Setter @ToString @EqualsAndHashCode
@Table(name = "question")
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "materia")
    private String materia;

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

    public Question(int id, String materia, String enunciado, Character respostaCorreta, Character respostaUsuario) {
        this.id = id;
        this.materia = materia;
        this.enunciado = enunciado;
        this.respostaCorreta = respostaCorreta;
        this.respostaUsuario = respostaUsuario;
    }

    public Question() {
    }

}


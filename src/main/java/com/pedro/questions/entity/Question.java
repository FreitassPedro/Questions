package com.pedro.questions.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;

@Entity
@Getter @Setter @ToString
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "materia")
    private String materia;
    @Column(name = "enunciado")
    private String enunciado;

    @Column(name = "alternativas")
    private HashMap<Character, String> alternativas;
    private Character respostaCorreta;
    private Character respostaUsuario;

    public void addAlternativas(Character letra, String textoOpcao) {
        if (alternativas == null) alternativas = new HashMap<>();

        alternativas.put(letra, textoOpcao);
    }

    public Question(int id, String materia, String enunciado, Character respostaUsuario, Character respostaCorreta) {
        this.id = id;
        this.materia = materia;
        this.enunciado = enunciado;
        this.respostaUsuario = respostaUsuario;
        this.respostaCorreta = respostaCorreta;
    }

    public Question() {
    }
}


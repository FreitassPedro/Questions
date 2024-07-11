package com.pedro.questions.entity.enums;

public enum Subject {
    TRIGONOMETRIA(Materia.MATEMATICA),
    ESCALA(Materia.MATEMATICA),
    TERMODINAMICA(Materia.FISICA),
    ATOMICA(Materia.QUIMICA),

    SEM_CLASSIFICACAO(Materia.SEM_CLASSIFICACAO);

    private final Materia materia;

    Subject(Materia materia) {
        this.materia = materia;
    }

    public Materia getMateria() {
        return materia;
    }
}
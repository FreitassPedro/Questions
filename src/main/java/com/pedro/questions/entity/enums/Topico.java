package com.pedro.questions.entity.enums;

public enum Topico {
    TRIGONOMETRIA(Materia.MATEMATICA),
    ESCALA(Materia.MATEMATICA),
    TERMODINAMICA(Materia.FISICA),
    ATOMICA(Materia.QUIMICA),

    SEM_CLASSIFICACAO(Materia.SEM_CLASSIFICACAO);

    private final Materia materia;

    Topico(Materia materia) {
        this.materia = materia;
    }

    public Materia getMateria() {
        return materia;
    }
}
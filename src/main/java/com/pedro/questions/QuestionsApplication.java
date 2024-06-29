package com.pedro.questions;

import com.pedro.questions.entity.Question;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuestionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {

		return runner -> {
			testandoPrograma();
		};

	}

	private void testandoPrograma() {

		Question q1 = new Question();
		q1.setMateria("Matematica");
		q1.setEnunciado("Calcule a área do quadrado de lado 2");
		q1.addAlternativas('A', "4.32cm²");
		q1.addAlternativas('B', "1cm²");
		q1.addAlternativas('C', "4cm²");
		q1.addAlternativas('D', "2.552cm²");

		q1.setRespostaCorreta('C');

		System.out.println(q1);

	}
}

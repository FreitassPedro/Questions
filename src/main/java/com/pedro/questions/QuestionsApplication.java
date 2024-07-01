package com.pedro.questions;

import com.pedro.questions.entity.Question;
import com.pedro.questions.repository.QuestionRepository;
import com.pedro.questions.service.QuestionService;
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
	public CommandLineRunner commandLineRunner(QuestionRepository questionRepository) {

		return runner -> {
			testandoPrograma(questionRepository);
		};

	}

	private void testandoPrograma(QuestionRepository questionRepository) {

		Question q1 = new Question();
		q1.setMateria("Matematica");
		q1.setEnunciado("Calcule a área do quadrado de lado 2");
		q1.addAlternativas('A', "4.32cm²");
		q1.addAlternativas('B', "1cm²");
		q1.addAlternativas('C', "4cm²");
		q1.addAlternativas('D', "2.552cm²");

		QuestionService questionService = new QuestionService(questionRepository);
		q1.setRespostaCorreta('C');

		System.out.println(q1);
		questionService.save(q1);

		System.out.println("----------------");
		Question byId = questionService.findById(1);
		System.out.println("Result:" + byId.toString());

	}
}

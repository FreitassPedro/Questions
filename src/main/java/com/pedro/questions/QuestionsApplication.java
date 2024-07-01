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

		QuestionService questionService = new QuestionService(questionRepository);
		Question q1 = new Question();

		q1.setMateria("Matematica");
		q1.setEnunciado("Calcule a área do quadrado de lado 2");
		q1.addAlternativas('A', "4.32cm²");
		q1.addAlternativas('B', "1cm²");
		q1.addAlternativas('C', "4cm²");
		q1.addAlternativas('D', "2.552cm²");

		q1.setRespostaCorreta('C');
		questionService.save(q1);

		Question q2 = createQuestion2();
		questionRepository.save(q2);


	}

	private static Question createQuestion2() {
		Question q2 = new Question();
		q2.setMateria("Matematica");
		q2.setEnunciado("A Petrobrás retomou recentemente o interesse em explorar a bacia da foz do rio Amazonas, após estudos que estimam grandes jazidas de petróleo na região. Sabe-se que a distância entre um dos poços de petróleo e a foz do rio Amazonas é de 500 km. \n" +
				"Qual é a escala de um mapa em que essa distância corresponde a 4 cm?");

		q2.addAlternativas('A', "1:400.000.");
		q2.addAlternativas('B', "1:4.000.000.");
		q2.addAlternativas('C', "1:125.000.");
		q2.addAlternativas('D', "1:1.250.000.");
		q2.addAlternativas('E', "1:12.500.000.");
		q2.setRespostaCorreta('E');
		return q2;
	}
}

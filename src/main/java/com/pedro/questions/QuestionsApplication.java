package com.pedro.questions;

import com.pedro.questions.entity.Question;
import com.pedro.questions.entity.UserStatistics;
import com.pedro.questions.entity.Users;
import com.pedro.questions.entity.enums.Materia;
import com.pedro.questions.entity.enums.Subject;
import com.pedro.questions.repository.QuestionRepository;
import com.pedro.questions.repository.UserStatisticRepository;
import com.pedro.questions.repository.UsersRepository;
import com.pedro.questions.service.QuestionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class QuestionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(QuestionRepository questionRepository,
											   UsersRepository usersRepository,
											   UserStatisticRepository userStatisticRepository) {

		return runner -> {
			criandoQuestoes(questionRepository);
			createUser(usersRepository);
		};

	}


	@Transactional
	private void createUser(UsersRepository usersRepository) {
		Users user1 = new Users();
		user1.setActive(false);
		user1.setEmail("pedro@email.com");
		user1.setPassword("$2a$12$2KuZTXvtyloztsgYhtGi8upp8sYlXdWmsJMpk5LbnuONPZdXu8.L6");

		// Salve o usuário e retorne a entidade gerenciada
		usersRepository.saveAndFlush(user1);
	}

	private void criandoQuestoes(QuestionRepository questionRepository) {
		QuestionService questionService = new QuestionService(questionRepository);
		Question q1 = new Question();

		q1.setMateria(Materia.MATEMATICA);
		q1.setSubject(Subject.TRIGONOMETRIA);
		q1.setEnunciado("Calcule a área do quadrado de lado 2");
		q1.addAlternativas('A', "4.32cm²");
		q1.addAlternativas('B', "1cm²");
		q1.addAlternativas('C', "4cm²");
		q1.addAlternativas('D', "2.552cm²");

		q1.setRespostaCorreta('C');
		questionService.save(q1);

		Question q2 = createQuestion();
		questionRepository.save(q2);

		Question q3 = new Question();
		q3.setMateria(Materia.MATEMATICA);
		q3.setSubject(Subject.TRIGONOMETRIA);
		q3.setEnunciado("...");
		q3.setRespostaCorreta('A');
		q3.addAlternativas('A', "Correta");
		q3.addAlternativas('B', "INCORRETA");
		q3.addAlternativas('C', "INCORRETA");
		questionRepository.save(q3);
	}

	private static Question createQuestion() {
		Question q2 = new Question();
		q2.setMateria(Materia.MATEMATICA);
		q2.setSubject(Subject.ESCALA);
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

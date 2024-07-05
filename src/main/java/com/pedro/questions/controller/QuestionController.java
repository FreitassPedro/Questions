package com.pedro.questions.controller;

import com.pedro.questions.entity.Question;
import com.pedro.questions.entity.Users;
import com.pedro.questions.service.QuestionService;
import com.pedro.questions.service.UserStatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final UserStatisticsService userStatisticsService;

    @GetMapping("/question")
    public String showQuestion(Model model) {
        Question byId = questionService.findById(2);
        model.addAttribute("question", byId);
        return "question/random-question";
    }

    @GetMapping("/question/{id}")
    public String showQuestionById(@PathVariable("id") int id,
                                   Model model) {
        Question byId = questionService.findById(id);
        model.addAttribute("question", byId);

        model.addAttribute("respostaUsuario", null);

        return "question/random-question";
    }

    @PostMapping("/processAnswer")
    public String processAnswer(@ModelAttribute("question") Question question) {

        Users currentUser =  getAuthentication();

        System.out.println("Resposta correta: " + question.getRespostaCorreta()
                + " | "
                + "Resposta do Usuario: " + question.getRespostaUsuario());

        userStatisticsService.processData(question, currentUser);


        System.out.println();
        return "redirect:/question";
    }

    private Users getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return userStatisticsService.getCurrentUser(authentication);
        }
        return null;
    }


}

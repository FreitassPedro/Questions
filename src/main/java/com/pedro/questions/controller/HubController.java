package com.pedro.questions.controller;

import com.pedro.questions.entity.Question;
import com.pedro.questions.entity.Users;
import com.pedro.questions.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HubController {

    @Autowired
    private QuestionService questionService;

    public String home() {
        return "question/random-question";
    }

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

        System.out.println("Resposta correta: " + question.getRespostaCorreta()
                + " | "
                + "Resposta do Usuario: " + question.getRespostaUsuario());
        if (question.getRespostaUsuario() == question.getRespostaCorreta()) {
            System.out.println("Resposta correta");
        } else System.out.println("Resposta errada");
        System.out.println();
        return "redirect:/question";
    }


}

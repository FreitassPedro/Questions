package com.pedro.questions.controller;

import com.pedro.questions.entity.Question;
import com.pedro.questions.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HubController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question")
    public String showQuestion(Model model) {
        Question byId = questionService.findById(2);
        model.addAttribute("question", byId);
        return "question/random-question";
    }

    @PostMapping("/responder")
    public String responderQuestao(Model model, Character resposta) {

        return "";
    }
}

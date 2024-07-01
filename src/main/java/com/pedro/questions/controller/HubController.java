package com.pedro.questions.controller;

import com.pedro.questions.entity.Question;
import com.pedro.questions.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HubController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question")
    public String showQuestion(Model model) {
        Question byId = questionService.findById(1);
        model.addAttribute("question", byId);
        return "question/random-question";
    }
}

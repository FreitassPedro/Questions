package com.pedro.questions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HubController {

    public String home() {
        return "question/random-question";
    }






}

package com.pedro.questions.controller;

import org.springframework.stereotype.Controller;


@Controller
public class HubController {

    public String home() {
        return "question/random-question";
    }




}

package com.pedro.questions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HubController {

    @GetMapping
    public String showHub() {
        return "olá mundo";
    }
}

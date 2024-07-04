package com.pedro.questions.controller;

import com.pedro.questions.entity.Users;
import com.pedro.questions.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new Users());
        return "user/register";
    }

    @PostMapping("/register/new")
    public String processRegistration(Users users) {
        usersService.save(users);
        return "redirect:/question";
    }

}

package com.pedro.questions.controller;

import com.pedro.questions.entity.Question;
import com.pedro.questions.entity.Users;
import com.pedro.questions.entity.UsersType;
import com.pedro.questions.service.UsersService;
import com.pedro.questions.service.UsersTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class UsersController {

    private final UsersService usersService;
    private final UsersTypeService usersTypeService;


    @GetMapping("/register")
    public String register(Model model) {
        List<UsersType> usersTypes = usersTypeService.getAll();
        model.addAttribute("getAllTypes", usersTypes);
        model.addAttribute("user", new Users());
        return "user/register";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }



    @PostMapping("/register/new")
    public String processRegistration(Users users) {
        usersService.save(users);
        return "redirect:/question";
    }

}

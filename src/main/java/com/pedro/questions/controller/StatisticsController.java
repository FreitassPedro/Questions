package com.pedro.questions.controller;

import com.pedro.questions.entity.UserStatistics;
import com.pedro.questions.entity.Users;
import com.pedro.questions.repository.QuestionAnsweredRepository;
import com.pedro.questions.service.UserStatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class StatisticsController {

    private final UserStatisticsService userStatisticsService;
    private QuestionAnsweredRepository questionAnsweredRepository;

    @GetMapping("/statistics")
    public String statistics(Model model) {
        Users currentUser = getAuthentication();
        UserStatistics userStats = userStatisticsService.findByUserId(currentUser);
        model.addAttribute("userStatistics", userStats);

        return "user/statistics";
    }

    private Users getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return userStatisticsService.getCurrentUser(authentication);
        }
        return null;
    }

}

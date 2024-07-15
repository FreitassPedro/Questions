package com.pedro.questions.controller;

import com.pedro.questions.dto.DataPoint;
import com.pedro.questions.entity.UserStatistics;
import com.pedro.questions.entity.Users;
import com.pedro.questions.repository.QuestionAnsweredRepository;
import com.pedro.questions.service.UserStatisticsService;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class StatisticsController {

    private final UserStatisticsService userStatisticsService;
    private QuestionAnsweredRepository questionAnsweredRepository;

    @GetMapping("/statistics")
    public String statistics(Model model) {
        Users currentUser = getAuthentication();
        UserStatistics userStatistics = userStatisticsService.findByUserId(currentUser);




        model.addAttribute("userStatistics", userStatistics);

        return "user/statistics";
    }

    @GetMapping("/myperformace")
    public String myPerformace(Model model) {
        Users currentUser = getAuthentication();
        UserStatistics userStatistics = userStatisticsService.findByUserId(currentUser);
        Hibernate.initialize(userStatistics.getSubjectStatistics());

        List<DataPoint> dataPointsRespondidas = userStatistics.getSubjectStatistics().entrySet().stream()
                .map(entry -> new DataPoint(entry.getKey().toString(), entry.getValue().getTotalAnswered()))
                .toList();

        List<DataPoint> dataPointsErradas = userStatistics.getSubjectStatistics().entrySet().stream()
                .map(entry -> new DataPoint(entry.getKey().toString(), entry.getValue().getTotalWrong()))
                .toList();

        model.addAttribute("userStatistics", userStatistics);
        model.addAttribute("dataPointsRespondidas", dataPointsRespondidas);
        model.addAttribute("dataPointsErradas", dataPointsErradas);

        return "user/subjectstatistics";
    }



    private Users getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return userStatisticsService.getCurrentUser(authentication);
        }
        return null;
    }




}

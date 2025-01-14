package com.spring.huntersleague.web.rest;

import com.spring.huntersleague.domain.Participation;
import com.spring.huntersleague.service.CompetitionService;
import com.spring.huntersleague.service.HuntService;
import com.spring.huntersleague.service.ParticipationService;
import com.spring.huntersleague.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final UserService userService;
    private final CompetitionService competitionService;
    private final ParticipationService participationService;
    private final HuntService huntService;

    public DashboardController(UserService userService, CompetitionService competitionService, ParticipationService participationService, HuntService huntService) {
        this.userService = userService;
        this.competitionService = competitionService;
        this.participationService = participationService;
        this.huntService = huntService;
    }


    @GetMapping("/statistics")
    public Map<String, Long> getStatistics() {
        Map<String, Long> statistics = new HashMap<>();
        statistics.put("users", userService.count());
        statistics.put("competitions", competitionService.count());
        statistics.put("participation", participationService.count());
        statistics.put("hunts", huntService.count());

        return statistics;
    }
}

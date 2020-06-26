package com.pma.running.service;

import com.pma.running.dto.GoalDtoRequest;
import com.pma.running.dto.GoalDtoResponse;
import com.pma.running.model.Goal;
import com.pma.running.repository.GoalRepository;
import com.pma.running.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Service
public class GoalService {

    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    @Autowired
    public GoalService(GoalRepository goalRepository, UserRepository userRepository) {
        this.goalRepository = goalRepository;
        this.userRepository = userRepository;
    }

    public Set<GoalDtoResponse> getAllGoalsByUser(Long id){
        Set<Goal> goals = goalRepository.findByUserId(id);
        Set<GoalDtoResponse> response = new HashSet<GoalDtoResponse>();

        for (Goal goalDto : goals) {
            GoalDtoResponse goal = new GoalDtoResponse();
            goal.setDistance(goalDto.getDistance());
            goal.setDuration(goalDto.getDuration());
            goal.setEnd_time(goalDto.getEndTime().toString());
            goal.setTimestamp(goalDto.getTimestampe().toString());
            goal.setTitle(goalDto.getTitle());
            response.add(goal);
        }

        return response;
    }

    public GoalDtoResponse saveGoal(GoalDtoRequest goalDtoRequest) throws Exception {

        Goal goal = new Goal();

        goal.setDistance(goalDtoRequest.getDistance());
        goal.setDuration(goalDtoRequest.getDuration());
        System.out.println(goalDtoRequest.getEnd_time() + " <<<<<<<<<<ISPISUJEM RADI PROVERE ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(goalDtoRequest.getEnd_time(), formatter);
        goal.setEndTime(dateTime);
        LocalDateTime dateTime1 = LocalDateTime.parse(goalDtoRequest.getTimestampe(), formatter);
        goal.setTimestampe(dateTime1);
        goal.setUser(userRepository.findById(goalDtoRequest.getUser_id()).orElse(null));
        goal.setTitle(goalDtoRequest.getTitle());
        GoalDtoResponse goalResponse = new GoalDtoResponse();
        try{
            goalRepository.save(goal);

            goalResponse.setDistance(goal.getDistance());
            goalResponse.setDuration(goal.getDuration());
            goalResponse.setEnd_time(goal.getEndTime().toString());
            goalResponse.setTimestamp(goal.getTimestampe().toString());
            goalResponse.setTitle(goal.getTitle());
        }catch (Exception e) {
            return null;
        }
        return goalResponse;
    }

}

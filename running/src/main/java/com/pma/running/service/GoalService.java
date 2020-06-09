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

    public Goal saveGoal(GoalDtoRequest goalDtoRequest) throws Exception {

        Goal goal = new Goal();

        goal.setDistance(goalDtoRequest.getDistance());
        goal.setDuration(goalDtoRequest.getDuration());
        goal.setEndTime(LocalDateTime.parse(goalDtoRequest.getEnd_time(), DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm")));
        goal.setTimestampe(LocalDateTime.parse(goalDtoRequest.getTimestampe(), DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm")));
        goal.setUser(userRepository.findById(goalDtoRequest.getUser_id()).orElse(null));
        goal.setTitle(goalDtoRequest.getTitle());
        try{
            goalRepository.save(goal);
        }catch (Exception e) {
            return null;
        }
        return goal;
    }

}

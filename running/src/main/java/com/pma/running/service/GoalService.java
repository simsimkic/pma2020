package com.pma.running.service;

import com.pma.running.dto.GoalDtoRequest;
import com.pma.running.dto.GoalDtoResponse;
import com.pma.running.model.Goal;
import com.pma.running.repository.GoalRepository;
import com.pma.running.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

    public Set<GoalDtoResponse> getAllGoalsByUser(Long id) throws ParseException {
        Set<Goal> goals = goalRepository.findByUserId(id);
        Set<GoalDtoResponse> response = new HashSet<GoalDtoResponse>();

        for (Goal goalDto : goals) {
            GoalDtoResponse goal = new GoalDtoResponse();
            goal.setDistance(goalDto.getDistance());
            goal.setId(goalDto.getId());
            goal.setDuration(goalDto.getDuration());


            DateFormat originalFormat = new SimpleDateFormat("yyyyy-mm-dd");
            DateFormat targetFormat = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
            Date date = originalFormat.parse(goalDto.getEndTime().toString());
            String formattedDate = targetFormat.format(date);


            goal.setEnd_time(formattedDate);
            goal.setTitle(goalDto.getTitle());
            goal.setArchived(goalDto.getArchived());
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
        goal.setArchived(false);
        GoalDtoResponse goalResponse = new GoalDtoResponse();
        try{
            goalRepository.save(goal);

            goalResponse.setDistance(goal.getDistance());
            goalResponse.setDuration(goal.getDuration());
            goalResponse.setEnd_time(goal.getEndTime().toString());
            goalResponse.setTimestamp(goal.getTimestampe().toString());
            goalResponse.setArchived(goal.getArchived());
            goalResponse.setTitle(goal.getTitle());
        }catch (Exception e) {
            return null;
        }
        return goalResponse;
    }

    public Boolean deleteGoal(Long id) throws Exception {
        if(goalRepository.findById(id) != null){
            try {
                goalRepository.deleteById(id);
                return true;
            }catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public GoalDtoResponse updateGoal(GoalDtoRequest goalDtoRequest) throws Exception {
        Goal goal = new Goal();
        goal.setId(goalDtoRequest.getId());
        System.out.println(goalDtoRequest.getId());
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
        System.out.println(goalDtoRequest.getArchived() + " PROVERE RADI ");
        goal.setArchived(goalDtoRequest.getArchived());
        GoalDtoResponse goalResponse = new GoalDtoResponse();
        try{
            goalRepository.save(goal);
            goalResponse.setDistance(goal.getDistance());
            goalResponse.setDuration(goal.getDuration());
            goalResponse.setEnd_time(goal.getEndTime().toString());
            goalResponse.setTimestamp(goal.getTimestampe().toString());
            goalResponse.setArchived(goal.getArchived());
            goalResponse.setTitle(goal.getTitle());
            goalResponse.setId(goal.getId());
        }catch (Exception e) {
            return null;
        }
        return goalResponse;
    }

}

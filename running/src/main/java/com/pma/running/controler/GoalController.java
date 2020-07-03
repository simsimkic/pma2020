package com.pma.running.controler;

import com.pma.running.dto.ActivityDto;
import com.pma.running.dto.ActivityResponseDto;
import com.pma.running.dto.GoalDtoRequest;
import com.pma.running.dto.GoalDtoResponse;
import com.pma.running.model.Activity;
import com.pma.running.model.Goal;
import com.pma.running.model.Post;
import com.pma.running.service.GoalService;
import com.pma.running.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin(origins = "", allowedHeaders = "", maxAge = 3600)
@RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
public class GoalController {


    private final GoalService goalService;

    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping("/getGoalsByUser/{userId}")
    public ResponseEntity<Set<GoalDtoResponse>> getGoalsByUser(@PathVariable Long userId) throws ParseException {
        Set<GoalDtoResponse> goals = goalService.getAllGoalsByUser(userId);
        return new ResponseEntity<Set<GoalDtoResponse>>(goals, HttpStatus.OK);
    }

    @PostMapping("/saveGoal")
    public ResponseEntity<GoalDtoResponse> saveGoal(@RequestBody GoalDtoRequest activityDto) throws Exception {
        GoalDtoResponse goal = goalService.saveGoal(activityDto);
        return new ResponseEntity<GoalDtoResponse>(goal, HttpStatus.OK);
    }

    @DeleteMapping("/deleteGoal/{goalId}")
    public ResponseEntity<Boolean> deleteGoal(@PathVariable Long goalId) throws Exception {
        Boolean goal = goalService.deleteGoal(goalId);
        return new ResponseEntity<Boolean>(goal, HttpStatus.OK);
    }

    @PutMapping("/updateGoal")
    public ResponseEntity<GoalDtoResponse> updateGoal(@RequestBody GoalDtoRequest goalDtoRequest) throws Exception {
        GoalDtoResponse goalResponse = goalService.updateGoal(goalDtoRequest);
        return new ResponseEntity<GoalDtoResponse>(goalResponse, HttpStatus.OK);
    }

}

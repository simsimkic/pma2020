package com.pma.running.dto;

import com.pma.running.model.User;
import lombok.Data;
import java.sql.Date;

@Data
public class GoalDtoRequest {

    private String title;
    private double duration;
    private double distance;
    private String timestampe;
    private String end_time;
    private Long user_id;

}

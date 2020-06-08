package com.pma.running.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
public class ActivityResponseDto {

    private Long id;
    private double duration;
    private double distance;
    private String dateTime;
    private int steps;
    private String encodedMap;
}

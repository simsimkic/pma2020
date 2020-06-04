package com.pma.running.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class EditUserRequest {

    private String name;
    private String username;
    private String address;
    private String email;
    private Date dateOfBirth;
    private String biography;
    private String height;
    private String weight;

}

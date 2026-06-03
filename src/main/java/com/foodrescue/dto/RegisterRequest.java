package com.foodrescue.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private String fullName;
    private String email;
    private String mobileNumber;
    private String location;
    private String password;
    private String role;
}
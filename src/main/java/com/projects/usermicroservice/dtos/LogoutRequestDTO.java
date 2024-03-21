package com.projects.usermicroservice.dtos;

import lombok.Getter;

@Getter
public class LogoutRequestDTO {
    private String token;
    private long userId;
}

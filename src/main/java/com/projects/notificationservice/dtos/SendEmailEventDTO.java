package com.projects.notificationservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailEventDTO {
    private String from;
    private String to;
    private String subject;
    private String emailBody;
}

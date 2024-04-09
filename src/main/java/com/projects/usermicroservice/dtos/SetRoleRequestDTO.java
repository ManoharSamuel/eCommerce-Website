package com.projects.usermicroservice.dtos;

import com.projects.usermicroservice.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SetRoleRequestDTO {
    private List<Long> roleIds;
}

package com.projects.usermicroservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends BaseModel {
    private String name;
    private String email;
    private String password;
    @OneToMany
    private List<Role> roles;
    @OneToMany(mappedBy = "user")
    private List<Session> sessions;
}

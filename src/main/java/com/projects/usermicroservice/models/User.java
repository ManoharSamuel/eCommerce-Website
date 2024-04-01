package com.projects.usermicroservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    @OneToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Token> tokens;
}

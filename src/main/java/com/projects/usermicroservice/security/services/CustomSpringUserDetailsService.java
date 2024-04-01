package com.projects.usermicroservice.security.services;

import com.projects.usermicroservice.models.User;
import com.projects.usermicroservice.repositories.UserRepository;
import com.projects.usermicroservice.security.models.CustomSpringUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomSpringUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    
    @Autowired
    public CustomSpringUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username).
                        orElseThrow(() -> new UsernameNotFoundException("user with the name" + username + "does not exist."));
        return new CustomSpringUserDetails(user);
    }
}

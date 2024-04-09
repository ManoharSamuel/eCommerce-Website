package com.projects.usermicroservice.services;

import com.projects.usermicroservice.dtos.SetRoleRequestDTO;
import com.projects.usermicroservice.dtos.UserDTO;
import com.projects.usermicroservice.exceptions.UserDoesNotExistException;
import com.projects.usermicroservice.models.Role;
import com.projects.usermicroservice.models.User;
import com.projects.usermicroservice.repositories.RoleRepository;
import com.projects.usermicroservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "UserServiceImpl")
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    private UserDTO getUserDTOFromUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }
    
    @Override
    public ResponseEntity<UserDTO> getUserDetails(long userId) throws UserDoesNotExistException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserDoesNotExistException("User with the given user id of "+userId+" does not exist.");
        }
        return new ResponseEntity<>(getUserDTOFromUser(userOptional.get()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> setUserRoles(long userId, List<Long> roleIds) throws UserDoesNotExistException {
        Optional<User> userOptional = userRepository.findById(userId);
        List<Role> roles = roleRepository.findAllByIdIn(roleIds);
        if (userOptional.isEmpty()) {
            throw new UserDoesNotExistException("User with the given user id of "+userId+" does not exist.");
        }
        User user = userOptional.get();
        user.setRoles(roles);
        userRepository.save(user);
        return new ResponseEntity<>(getUserDTOFromUser(user), HttpStatus.OK);
    }
}

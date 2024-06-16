package org.example.tpo_12.auth;

import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserDTOMapper {

    public static UserDTO map(User user){
        return new UserDTO(
                user.getEmail(),
                user.getPassword(),
                user.getRoles()
                        .stream()
                        .map(UserRole::getName)
                        .collect(Collectors.toSet())
        );
    }

   /* public User map(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        return user;
    }*/
}

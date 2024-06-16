package org.example.tpo_12.auth;

import jakarta.transaction.Transactional;
import org.example.tpo_12.repository.UserRepository;
import org.example.tpo_12.repository.UserRoleRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository){
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public Optional<UserDTO> findUserCredentialsByEmail(String email){
        return userRepository.findByEmail(email)
                .map(UserDTOMapper::map);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUserByEmail(String email){
        userRepository.deleteByEmail(email);
    }

    public Set<String> findRolesByEmail(String email){
        Optional<UserDTO> user = findUserCredentialsByEmail(email);
        if (user.isPresent())
            return user.get().getRoles();
        return Set.of();
    }

    private boolean isCurrentUserAdmin() {
        return SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }

    @Transactional
    public void registerReader(UserRegisterDTO userDTO){
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(randomPasswordEncoding(userDTO.getPassword()));
//        user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder()
//                .encode(userDTO.getPassword()));
        Optional<UserRole> userRole = userRoleRepository.findByName("READER");
        userRole.ifPresentOrElse(role -> user.getRoles().add(role), Exception::new);
        userRepository.save(user);
    }

    @Transactional
    public void registerPublisher(UserRegisterDTO userDTO){
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(randomPasswordEncoding(userDTO.getPassword()));
//        user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder()
//                .encode(userDTO.getPassword()));
        Optional<UserRole> userRole = userRoleRepository.findByName("PUBLISHER");
        Optional<UserRole> userRole2 = userRoleRepository.findByName("READER");
        userRole.ifPresentOrElse(role -> user.getRoles().add(role), Exception::new);
        userRole2.ifPresentOrElse(role -> user.getRoles().add(role), Exception::new);
        userRepository.save(user);
    }

    private String randomPasswordEncoding(String password) {
        Random random = new Random();
        int randomNumber = random.nextInt(5) + 1;
        String pass = "";
        switch (randomNumber) {
            case 1:
                pass = "{bcrypt}" + new BCryptPasswordEncoder().encode(password);
            case 2:
                pass = "{MD5}" + new MessageDigestPasswordEncoder("MD5").encode(password);
            case 3:
                pass = "{noop}" + password;
            case 4:
                pass = "{SHA-256}" + new MessageDigestPasswordEncoder("SHA-256").encode(password);
            case 5:
                pass = "{pbkdf2}" + new Pbkdf2PasswordEncoder("secret", 5, 100, 10).encode(password);
        }
        return pass;
    }

    @Transactional
    public void updateUserRoles(String email, Set<String> roles) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Clear existing roles and add new roles
            user.getRoles().clear();
            roles.forEach(roleName -> {
                String roleNameWithoutPrefix = roleName.replace("ROLE_", ""); // Remove prefix
                UserRole role = userRoleRepository.findByName(roleNameWithoutPrefix)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleNameWithoutPrefix));
                user.getRoles().add(role);
            });

            userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with email: " + email);
        }
    }
}

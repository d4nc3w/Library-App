package org.example.tpo_12.auth;

import java.util.Set;

public class UserDTO {

    private String email;
    private Set<String> roles;
    private String password;

    public UserDTO(String email, String password, Set<String> roles){
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}

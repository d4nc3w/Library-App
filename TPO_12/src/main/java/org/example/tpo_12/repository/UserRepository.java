package org.example.tpo_12.repository;

import org.example.tpo_12.auth.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    List<User> findAll();
    void deleteByEmail(String email);
    List<User> findAllUsersByRolesName(String role);
}

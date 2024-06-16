package org.example.tpo_12.repository;

import org.example.tpo_12.auth.UserRole;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
    Optional<UserRole> findByName(String name);
}

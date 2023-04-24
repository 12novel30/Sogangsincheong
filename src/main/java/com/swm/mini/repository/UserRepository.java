package com.swm.mini.repository;

import com.swm.mini.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    long deleteById(String userId);

    boolean existsById(String userId);

    Optional<User> findById(String userId);
}
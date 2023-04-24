package com.swm.mini.repository;

import com.swm.mini.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    long deleteByUserId(String userId);

    boolean existsByUserId(String userId);

    Optional<User> findByUserId(String userId);
}
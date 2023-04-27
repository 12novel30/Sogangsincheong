package com.swm.mini.repository;

import com.swm.mini.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, String> {
}

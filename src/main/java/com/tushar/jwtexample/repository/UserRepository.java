package com.tushar.jwtexample.repository;

import com.tushar.jwtexample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
}

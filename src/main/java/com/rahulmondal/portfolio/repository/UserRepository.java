package com.rahulmondal.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahulmondal.portfolio.models.User;

public interface  UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);
    
}

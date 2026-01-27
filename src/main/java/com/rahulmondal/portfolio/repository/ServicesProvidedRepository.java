package com.rahulmondal.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahulmondal.portfolio.models.ServicesProvided;

public interface ServicesProvidedRepository extends JpaRepository<ServicesProvided,Long> {
    List<ServicesProvided> findByUserId(Long userId);;
}

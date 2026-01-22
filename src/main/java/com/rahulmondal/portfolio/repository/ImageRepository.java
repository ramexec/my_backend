package com.rahulmondal.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahulmondal.portfolio.models.Image;

public interface ImageRepository extends JpaRepository<Image,Long> {

}

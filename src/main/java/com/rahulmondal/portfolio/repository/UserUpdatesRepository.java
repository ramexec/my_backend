package com.rahulmondal.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahulmondal.portfolio.models.User;
import com.rahulmondal.portfolio.models.UserUpdates;

public interface UserUpdatesRepository extends JpaRepository<UserUpdates,Long> {

    List<UserUpdates> findAllByUser(User user);
    List<UserUpdates> findTop50ByUserOrderByCreatedAtDesc(User user);

}

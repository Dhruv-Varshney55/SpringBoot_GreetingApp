package com.example.GreetingApp.repository;

import com.example.GreetingApp.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

}
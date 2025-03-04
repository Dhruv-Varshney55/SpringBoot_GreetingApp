package com.example.GreetingApp.repository;

import com.example.GreetingApp.model.Greeting;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface GreetingRepository extends JpaRepositoryImplementation<Greeting, Long>{

}
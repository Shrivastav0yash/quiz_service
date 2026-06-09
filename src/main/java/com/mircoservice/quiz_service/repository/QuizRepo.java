package com.mircoservice.quiz_service.repository;


import com.microservices.quiz_app.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Integer> {
}

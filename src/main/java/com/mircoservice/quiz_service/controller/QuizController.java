package com.mircoservice.quiz_service.controller;

import com.microservices.quiz_app.entities.QuestionWrapper;
import com.microservices.quiz_app.entities.Response;
import com.microservices.quiz_app.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        quizService.createQuiz(category, numQ, title);
        return new ResponseEntity<>("Quiz Created",HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        List<QuestionWrapper> quizQuestions = quizService.getQuizQuestions(id);
        return new ResponseEntity<>(quizQuestions, HttpStatus.OK);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<?> submitQuiz(@PathVariable Integer id, @RequestBody  List<Response> responses){
        int result = quizService.calculateResult(id, responses);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

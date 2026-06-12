package com.mircoservice.quiz_service.controller;

import com.mircoservice.quiz_service.entities.QuestionWrapper;
import com.mircoservice.quiz_service.entities.QuizDto;
import com.mircoservice.quiz_service.entities.Response;
import com.mircoservice.quiz_service.service.QuizService;
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
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        quizService.createQuiz(quizDto.getCategoryName(), quizDto.getNumQuestions(), quizDto.getTitle());
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

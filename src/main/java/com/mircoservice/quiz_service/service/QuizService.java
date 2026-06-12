package com.mircoservice.quiz_service.service;


import com.mircoservice.quiz_service.entities.QuestionWrapper;
import com.mircoservice.quiz_service.entities.Quiz;
import com.mircoservice.quiz_service.entities.Response;
import com.mircoservice.quiz_service.feign.QuizInterface;
import com.mircoservice.quiz_service.repository.QuizRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepo quizRepo;
    private final QuizInterface quizInterface;

    public void createQuiz(String category, int numQ, String title) {
        List<Integer> questions = quizInterface.getQuestionForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionList(questions);
        quizRepo.save(quiz);
    }

    public List<QuestionWrapper> getQuizQuestions(Integer id) {

        Quiz quiz  = quizRepo.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionList();
        return quizInterface.getQuestionFromId(questionIds).getBody();
    }

    public int calculateResult(Integer id, List<Response> responses) {
        return quizInterface.getScore(responses).getBody();
    }
}

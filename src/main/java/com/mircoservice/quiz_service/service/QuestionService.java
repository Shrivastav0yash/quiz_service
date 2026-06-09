package com.mircoservice.quiz_service.service;

import com.microservices.quiz_app.entities.Question;
import com.microservices.quiz_app.repository.QuestionsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionsRepo questionsRepo;

    public List<Question> getAllQuestions(){
        return questionsRepo.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionsRepo.findByCategory(category);
    }

    public String addQuestion(Question question) {
        questionsRepo.save(question);
        return "Question add successfully";
    }

    public void deleteQuestion(Long id) {
        questionsRepo.deleteById(id);
    }
}

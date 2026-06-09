package com.mircoservice.quiz_service.service;

import com.microservices.quiz_app.entities.Question;
import com.microservices.quiz_app.entities.QuestionWrapper;
import com.microservices.quiz_app.entities.Quiz;
import com.microservices.quiz_app.entities.Response;
import com.microservices.quiz_app.repository.QuestionsRepo;
import com.microservices.quiz_app.repository.QuizRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepo quizRepo;
    private final QuestionsRepo questionsRepo;

    public void createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionsRepo.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionList(questions);
        quizRepo.save(quiz);
    }

    public List<QuestionWrapper> getQuizQuestions(Integer id) {
        return null;
    }

    public int calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizRepo.findById(id).get();
        List<Question> questions = quiz.getQuestionList();
        int result = 0;
        int i = 0;
        for(Response response : responses){
            System.out.println(response.getResponse()+ "  --->>>> "+ questions.get(i).getRightAnswer());
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                result++;
            i++;
        }
        return result;
    }
}

package com.raj.quiz_service.Services;

import com.raj.quiz_service.Feign.QuizInterface;
import com.raj.quiz_service.Model.QuestionWrapper;
import com.raj.quiz_service.Model.Quiz;
import com.raj.quiz_service.Model.Response;
import com.raj.quiz_service.Repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepo quizRepo;


    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        try {
            List<Integer> questionIds = quizInterface.generateQuestions(category, numQ).getBody();
            Quiz quiz =new Quiz();
            quiz.setTitle(title);
            quiz.setQuestionIds(questionIds);
            quizRepo.save(quiz);
            return new ResponseEntity<>("success",HttpStatus.OK);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizById(int id) {
        try {
            Optional<Quiz> quiz=quizRepo.findById(id);
            List<QuestionWrapper> questionWrappers =quizInterface.getQuestionsById(quiz.get().getQuestionIds()).getBody();
            return new ResponseEntity<>(questionWrappers,HttpStatus.OK);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Integer> calculateMarks(int id, List<Response> responses) {
        int marksObtained = quizInterface.getScore(responses).getBody();
        return new ResponseEntity<>(marksObtained,HttpStatus.OK);


    }
}

package com.raj.quiz_service.Controller;


import com.raj.quiz_service.Model.QuestionWrapper;
import com.raj.quiz_service.Model.QuizDet;
import com.raj.quiz_service.Model.Response;
import com.raj.quiz_service.Services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDet quizDet){
        return quizService.createQuiz(quizDet.getCategory(),quizDet.getNumQ(),quizDet.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id){
        return quizService.getQuizById(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> calculateMarks(@PathVariable int id,@RequestBody List<Response> responses){
        return quizService.calculateMarks(id,responses);
    }
}

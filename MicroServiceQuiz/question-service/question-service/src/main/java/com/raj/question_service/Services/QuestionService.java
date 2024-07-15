package com.raj.question_service.Services;

import com.raj.question_service.Model.QuestionWrapper;
import com.raj.question_service.Model.Questions;
import com.raj.question_service.Model.Response;
import com.raj.question_service.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<List<Questions>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public void saveAll(List<Questions> questions) {
        questionRepository.saveAll(questions);
    }

    public ResponseEntity<List<Questions>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepository.findByCategory(category), HttpStatus.OK);
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> save(Questions questions) {
        try {
             questionRepository.save(questions);
             return new ResponseEntity<>("success",HttpStatus.CREATED);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>("Failed",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> generateQuestions(String category, int numQ) {
        return new ResponseEntity<>(questionRepository.findByCategoryRandomLimitNumQ(category,numQ),HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestions(List<Integer> questionsId) {
        List<QuestionWrapper> questionWrappers =new ArrayList<QuestionWrapper>();

        for(Integer id : questionsId){
            Questions questions=questionRepository.findById(id).get();
            int qId= questions.getId();
            String catg=questions.getCategory();
            String ques=questions.getQuestion();
            String op1 =questions.getOption1();
            String op2 =questions.getOption2();
            String op3 =questions.getOption3();
            String op4 =questions.getOption4();
            questionWrappers.add(new QuestionWrapper(qId,catg,ques,op1,op2,op3,op4));
        }
        return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int score=0;
        for(Response r : responses){
            String ans = questionRepository.findById(r.getId()).get().getAns();
            if(ans.equals(r.getResponse()))
                score+=1;
        }
        return new ResponseEntity<>(score,HttpStatus.OK);
    }
}

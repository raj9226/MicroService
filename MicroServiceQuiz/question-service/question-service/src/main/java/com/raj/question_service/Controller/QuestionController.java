package com.raj.question_service.Controller;


import com.raj.question_service.Model.QuestionWrapper;
import com.raj.question_service.Model.Questions;
import com.raj.question_service.Model.Response;
import com.raj.question_service.Services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

//    List<Questions> questions = List.of(new Questions(1,"Java","Easy"," Which of the following option leads to the portability and security of Java?",
//            "Bytecode is executed by JVM","The applet makes the Java code secure and portable","Use of exception handling",
//            "Dynamic binding between objects","Bytecode is executed by the JVM."),
//            new Questions(2,"Java","Easy","Which of the following is not a Java features?","Dynamic","Architecture Neutral","Use of pointers",
//                    "Object-oriented","Use of pointers"),
//            new Questions(3,"Java","Easy","The \\u0021 article referred to as a","Unicode escape sequence","Octal escape","Hexadecimal","Line feed","Unicode escape sequence"),
//            new Questions(4,"Java","Easy","_____ is used to find and fix bugs in the Java programs.","JVM","JRE","JDK","JDB","JDB"),
//            new Questions(5,"Java","Easy","What is the return type of the hashCode() method in the Object class?","Object","int","long","void","int"),
//            new Questions(6,"Python","Easy","Who developed Python Programming Language?","Wick van Rossum","Rasmus Lerdorf","Guido van Rossum","Niene Stom","Guido van Rossum"),
//            new Questions(7,"Python","Easy","Which type of Programming does Python support?","object-oriented programming"," structured programming","functional programming",
//                    "all of the mentioned","all of the mentioned"),
//            new Questions(8,"Python","Easy"," Is Python case sensitive when dealing with identifiers?","no","yes","machine dependent",
//                    "none of the mentioned","yes"),
//            new Questions(9,"Python","Easy","Which of the following is the correct extension of the Python file?",".python",".pl",".py",".p",".py"),
//            new Questions(10,"Python","Easy","Is Python code compiled or interpreted?","Python code is both compiled and interpreted","Python code is neither compiled nor interpreted",
//                    "Python code is only compiled","Python code is only interpreted","Python code is both compiled and interpreted"));

    @Autowired
    QuestionService questionService;

    @GetMapping("questions")
    public ResponseEntity<List<Questions>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

//    @PostMapping("loadData")
//    public String loadData(){
//        questionService.saveAll(questions);
//        return "Success";
//    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category)
    {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Questions questions)
    {
        return questionService.save(questions);

    }

    //generate

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> generateQuestions(@RequestParam String category,@RequestParam int numQ){
        return questionService.generateQuestions(category,numQ);

    }

    //questions/{questionId}

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(@RequestBody List<Integer> questionsId){
        return questionService.getQuestions(questionsId);
    }


    //calculateScore

    @PostMapping("score")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return  questionService.getScore(responses);
    }


}

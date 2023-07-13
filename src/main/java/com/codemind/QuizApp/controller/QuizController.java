package com.codemind.QuizApp.controller;

import com.codemind.QuizApp.entity.QuestionWrapper;
import com.codemind.QuizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    private QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title){

        return quizService.crateQuiz(category,numQ,title);
    }

    @GetMapping("get/{title}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable String title){

        return quizService.getQuizQuestionByTitle(title);

    }
}

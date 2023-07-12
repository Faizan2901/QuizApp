package com.codemind.QuizApp.controller;

import com.codemind.QuizApp.entity.Question;
import com.codemind.QuizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/allQuestions")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{cat}")
    public List<Question> getQuestionByCategory(@PathVariable("cat") String category){return questionService.getQuestionByCategory(category);}

    @PostMapping("questions")
    public String addQuestion(@RequestBody Question question){
       return  questionService.addQuestion(question);
    }

    @PutMapping("questions")
    public String updateQuestion(@RequestBody Question question){
        return questionService.updateQuestion(question);
    }

    @DeleteMapping("questions/{id}")
    public String deleteQuestion(@PathVariable int id){
        return questionService.deleteQuestion(id);
    }

}

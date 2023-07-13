package com.codemind.QuizApp.service;

import com.codemind.QuizApp.dao.QuestionDAO;
import com.codemind.QuizApp.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionDAO questionDAO;



    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDAO.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category){
        try {
            return new ResponseEntity<>(questionDAO.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Question question) {
        if(question.getQuestionTitle() != null){
            questionDAO.save(question);
            return new ResponseEntity<>("New ID which is generated : "+question.getId(),HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Data is Null",HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> updateQuestion(Question question) {

             try{
                questionDAO.save(question);

                return new ResponseEntity<>("Update data which Id is : " + question.getId() + " and Question is : " + question.getQuestionTitle(), HttpStatus.CREATED);
            }catch (Exception e){
                 e.printStackTrace();
             }
        return new ResponseEntity<>("ID not found : "+question.getId(),HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<String> deleteQuestion(int id) {
        Optional<Question> question=questionDAO.findById(id);
        if(question.isPresent()){
            questionDAO.deleteById(id);
            return new ResponseEntity<>("Question is deleted which ID is : "+id,HttpStatus.OK);
        }
        return new ResponseEntity<>("ID not found : "+id,HttpStatus.NOT_FOUND);
    }
}

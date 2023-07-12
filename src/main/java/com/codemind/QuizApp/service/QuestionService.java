package com.codemind.QuizApp.service;

import com.codemind.QuizApp.dao.QuestionDAO;
import com.codemind.QuizApp.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private QuestionDAO questionDAO;

    @Autowired
    public QuestionService(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    public List<Question> getAllQuestions() {
        return questionDAO.findAll();
    }

    public List<Question> getQuestionByCategory(String category){
        return questionDAO.findByCategory(category);
    }

    public String addQuestion(Question question) {
        questionDAO.save(question);
        return "New ID which is generated : "+question.getId();
    }

    public String updateQuestion(Question question) {

        questionDAO.save(question);

        return "Update data which Id is : "+question.getId()+" and Question is : "+question.getQuestionTitle();

    }

    public String deleteQuestion(int id) {
        questionDAO.deleteById(id);
        return "Question is deleted which ID is : "+id;
    }
}

package com.codemind.QuizApp.service;

import com.codemind.QuizApp.dao.QuestionDAO;
import com.codemind.QuizApp.dao.QuizDAO;
import com.codemind.QuizApp.entity.Question;
import com.codemind.QuizApp.entity.QuestionWrapper;
import com.codemind.QuizApp.entity.Quiz;
import com.codemind.QuizApp.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService  {

    @Autowired
    private QuizDAO quizDAO;

    @Autowired
    private QuestionDAO questionDAO;


    public ResponseEntity<String> crateQuiz(String category, int numQ, String title) {

        List<Question> questions=questionDAO.findRandomQuestionsByCategory(category,numQ);

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizDAO.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionByTitle(String title) {

        Optional<Quiz> quiz= Optional.ofNullable(quizDAO.findByTitle(title));
        List<Question> questionsFromDB=quiz.get().getQuestions();

        List<QuestionWrapper> questionForUser=new ArrayList<>();

        for(Question q: questionsFromDB){
            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4(),q.getQuestionTitle());
            questionForUser.add(qw);
        }


        return new ResponseEntity<>(questionForUser,HttpStatus.OK);

    }

    public ResponseEntity<String> calculateQuiz(String title, List<Response> responses) {

        Optional<Quiz> quiz= Optional.ofNullable(quizDAO.findByTitle(title));
        List<Question> questionsFromDB=quiz.get().getQuestions();
        int right=0;
        int i=0;

        for (Response res:responses)
        {
            if(res.getResponse().equals(questionsFromDB.get(i).getRightAnswer()))
            {
                right++;
            }
            i++;
        }
        return new ResponseEntity<>("Your '"+title+"' quiz score is : "+right,HttpStatus.OK);
    }
}

package com.codemind.QuizApp.dao;

import com.codemind.QuizApp.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDAO extends JpaRepository<Quiz,Integer> {

    Quiz findByTitle(String title);

}

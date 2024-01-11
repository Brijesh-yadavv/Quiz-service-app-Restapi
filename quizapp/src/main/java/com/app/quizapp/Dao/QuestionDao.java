package com.app.quizapp.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.quizapp.model.Question;
import java.util.List;


@Repository
public interface QuestionDao   extends JpaRepository<Question,Integer> {

    List<Question> findByDifficultyLevel(String difficultyLevel);


    @Query(value = "SELECT * FROM question Q where Q.difficulty_level = :difficultyLevel ORDER BY RAND() LIMIT :numQ",nativeQuery = true)
    List<Question> findRandomQuestionByLevel(String difficultyLevel, int numQ);
    
}

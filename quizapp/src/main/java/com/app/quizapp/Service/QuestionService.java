package com.app.quizapp.Service;

import com.app.quizapp.Dao.QuestionDao;
import com.app.quizapp.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Question>> getQuestionsByDifficultyLevel(String level) {

        try {
            return new ResponseEntity<>(questionDao.findByDifficultyLevel(level), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Question question) {

        try {

            questionDao.save(question);
            return new ResponseEntity<>("success", HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("adding fail", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    public ResponseEntity<String> deleteById(int id) {
        try {
            Optional<Question> q = questionDao.findById(id);
            System.out.println(q);
            if (q.isEmpty()) {
                return new ResponseEntity<>("deletion fail", HttpStatus.BAD_REQUEST);
            }
            questionDao.deleteById(id);
            return new ResponseEntity<>("deletion success", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("deletion fail", HttpStatus.BAD_REQUEST);

    }

}

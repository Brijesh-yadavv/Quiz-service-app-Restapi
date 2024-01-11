package com.app.quizapp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.quizapp.Dao.QuestionDao;
import com.app.quizapp.Dao.QuizDao;
import com.app.quizapp.Dao.Responsee;
import com.app.quizapp.model.Question;
import com.app.quizapp.model.QuestionWrapper;
import com.app.quizapp.model.Quiz;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String level, int numQ, String title) {
        List<Question> ques = questionDao.findRandomQuestionByLevel(level, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestion(ques);
        System.out.println(quiz);
        quizDao.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestion();

        List<QuestionWrapper> questionWrappersForUser = new ArrayList<>();

        for (Question q : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(),
                    q.getOption3(), q.getOption4());
            questionWrappersForUser.add(qw);
        }

        return new ResponseEntity<>(questionWrappersForUser, HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer id,List<Responsee>response) {
        Quiz quiz=quizDao.findById(id).get();
        List<Question> question=quiz.getQuestion();
        int count=0;
int i=0;

        for(Responsee resp:response){
            if(resp.getResponse().equals(question.get(i).getRightAnswer())){
                count++;
            }

            i++;
        }

        return new ResponseEntity<>(count,HttpStatus.OK);
    }

}

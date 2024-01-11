package com.app.quizapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.quizapp.Dao.Responsee;
import com.app.quizapp.Service.QuizService;
import com.app.quizapp.model.Question;
import com.app.quizapp.model.QuestionWrapper;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String level, @RequestParam int numQ,
            @RequestParam String title) {
        System.out.println("hiiiiiiiiiiiiiiiiiiiiiii");
        return quizService.createQuiz(level, numQ, title);

    }

    @GetMapping("getQuiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id) {

        return quizService.getQuizQuestion(id);

    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@RequestBody List<Responsee> response, @PathVariable Integer id){
        return quizService.calculateResult(id,response);
    }


}

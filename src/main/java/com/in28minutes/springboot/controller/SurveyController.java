package com.in28minutes.springboot.controller;

import com.in28minutes.springboot.error.AuthorizationError;
import com.in28minutes.springboot.error.OtherError;
import com.in28minutes.springboot.model.Question;
import com.in28minutes.springboot.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

/**
 * Created by IrianLaptop on 7/6/2017.
 */

@RestController
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    @Qualifier(value = "dummy")
    private String dummy;

    @GetMapping("/surveys/{surveyId}/questions")
    public List<Question> retrieveQuestions(@PathVariable String surveyId) {
/*
        if(true) throw new AuthorizationError();
*/
        return surveyService.retrieveQuestions(surveyId);
    }


    // GET "/surveys/{surveyId}/questions/{questionId}"
    @GetMapping("/surveys/{surveyId}/questions/{questionId}")
    public ResponseEntity<Question> retrieveDetailsForQuestion(@PathVariable String surveyId,
                                               @PathVariable String questionId) {
        /*return surveyService.retrieveQuestion(surveyId, questionId);*/
        return ResponseEntity.ok(surveyService.retrieveQuestion(surveyId, questionId));
    }


    // /surveys/{surveyId}/questions
    @PostMapping("/surveys/{surveyId}/questions")
    public ResponseEntity<Void> addQuestionToSurvey(
            @PathVariable String surveyId, @RequestBody Question newQuestion) {

        Question question = surveyService.addQuestion(surveyId, newQuestion);

        if (question == null)
            return ResponseEntity.noContent().build();

        // Success - URI of the new resource in Response Header
        // Status - created
        // URI -> /surveys/{surveyId}/questions/{questionId}
        // question.getQuestionId()
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{id}").buildAndExpand(question.getId()).toUri();

        // Status
        return ResponseEntity.created(location).build();
    }

}

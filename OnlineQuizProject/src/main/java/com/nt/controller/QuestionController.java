package com.nt.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.Question;
import com.nt.service.IQuestionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:5175")
@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuestionController {

	private final IQuestionService questionService;
	
	@PostMapping("/create-new-question")
	public ResponseEntity<Question> createQuestion(@Valid @RequestBody Question question){
		System.out.println("The correct answer is ::::"+question.getCorrectAnswers());
		Question createdQuestion = questionService.createQuestion(question);
		return  ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
	}
	
	@GetMapping("/all-questions")
	public ResponseEntity<List<Question>> getAllQuestions(){
		List<Question> questions = questionService.getAllQuestion() ;
		return ResponseEntity.ok(questions);
	}
	
	@GetMapping("/question/{id}")
	public ResponseEntity<Question> getQuestionById(@PathVariable Long id)throws ChangeSetPersister.NotFoundException{
		Optional<Question> theQuestion = questionService.getQuestionById(id);
	  if(theQuestion.isPresent()) {
		  return ResponseEntity.ok(theQuestion.get());
	  }
	  else {
		   throw new ChangeSetPersister.NotFoundException();
	  }
	}
	
	@PutMapping("/question/{id}/update")
	public ResponseEntity<Question> updateQuestion(@PathVariable Long id,@RequestBody Question question) throws ChangeSetPersister.NotFoundException{
		Question updatedQuestion = questionService.updateQuestion(id, question);
		return ResponseEntity.ok(updatedQuestion);
	}
	
	@DeleteMapping("question/{id}/delete")
	public ResponseEntity<Void> deleteQuestion(@PathVariable Long id){
		questionService.deleteQuestion(id);
	return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/subjects")
	public ResponseEntity<List<String>> getAllSubjects(){
		List<String> subjects = questionService.getAllSubjects();
	return ResponseEntity.ok(subjects);
	}
	
	@GetMapping("/quiz/fetch-questions-for-user")
	public ResponseEntity<List<Question>> getQuestionsForUser(@RequestParam Integer numOfQuestions,
			                                                               @RequestParam String subject){
		List<Question> allQuestions = questionService.getQuestionForUser(numOfQuestions, subject);
		List<Question> mutableQuestions = new ArrayList<>(allQuestions);
		Collections.shuffle(mutableQuestions);
		
		int availableQuestions = Math.min(numOfQuestions, mutableQuestions.size());
		List<Question> randomQuestions  = mutableQuestions.subList(0, availableQuestions);
		return ResponseEntity.ok(randomQuestions);
		
	}
}













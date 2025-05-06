package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister;

import com.nt.model.Question;

public interface IQuestionService {

	Question createQuestion(Question question);
	
	List<Question> getAllQuestion();
	
	Optional<Question> getQuestionById(Long id);
	
	List<String> getAllSubjects();
	
     Question updateQuestion(Long id,Question question) throws ChangeSetPersister.NotFoundException;
     
     void deleteQuestion(Long id);
     
     List<Question> getQuestionForUser(Integer numOfQuestions,String subject);
     
	
}

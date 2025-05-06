package com.nt.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nt.model.Question;
import com.nt.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {
	
	
	private final QuestionRepository questionRepository;

	@Override
	public Question createQuestion(Question question) {
		System.out.println("get correct answer :"+question.getCorrectAnswers());
		return questionRepository.save(question);
	}

	@Override
	public List<Question> getAllQuestion() {
		return questionRepository.findAll();
	}

	@Override
	public Optional<Question> getQuestionById(Long id) {
		return questionRepository.findById(id);
	}

	@Override
	public List<String> getAllSubjects() {
		return questionRepository.findDistinctSubject();
	}

	@Override
	public Question updateQuestion(Long id, Question question) throws ChangeSetPersister.NotFoundException{
		Optional<Question> theQuestion = this.getQuestionById(id);
	  if(theQuestion.isPresent()) {
		  Question updatedQuestion = theQuestion.get();
		  updatedQuestion.setQuestion(question.getQuestion());
		  updatedQuestion.setCorrectAnswers(question.getCorrectAnswers());
		  updatedQuestion.setChoices(question.getChoices());
		  return questionRepository.save(updatedQuestion);
	  }
	  else {
		  throw new ChangeSetPersister.NotFoundException();
	  }
	}

	@Override
	public void deleteQuestion(Long id) {
		 questionRepository.deleteById(id);

	}

	@Override
	public List<Question> getQuestionForUser(Integer numOfQuestions, String subject) {
		Pageable pageable = PageRequest.of(0, numOfQuestions);
		return questionRepository.findBySubject( subject,pageable).getContent();
	}

}

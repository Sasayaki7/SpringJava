package com.sasayaki7.dojooverflow.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sasayaki7.dojooverflow.models.Answer;
import com.sasayaki7.dojooverflow.models.Question;
import com.sasayaki7.dojooverflow.models.Tags;
import com.sasayaki7.dojooverflow.repositories.AnswerRepository;
import com.sasayaki7.dojooverflow.repositories.QuestionRepository;
import com.sasayaki7.dojooverflow.repositories.TagsRepository;

@Service
public class ApiService {
	private final AnswerRepository ansRepo;
	private final TagsRepository tagsRepo;
	private final QuestionRepository questionRepo;
	
	public ApiService(AnswerRepository ansRepo, TagsRepository tagsRepo, QuestionRepository questionRepo) {
		this.ansRepo = ansRepo;
		this.tagsRepo = tagsRepo;
		this.questionRepo = questionRepo;
	}
	
	public Question createQuestion(Question q) {
		return questionRepo.save(q);
	}
	
	public Question updateQuestion(Question q) {
		return questionRepo.save(q);
	}
	
	public Question findQuestion(Long id) {
		Optional<Question> maybeQ = questionRepo.findById(id);
		if (maybeQ.isPresent()) {
			return maybeQ.get();
		}
		else {
			return null;
		}
	}
	
	
	public Question createQuestionWithTags(String text, List<String> tags) {
	
		Question q = this.createQuestion(new Question(text));
		List<Tags> tagsLists = new ArrayList<Tags>();
		for (String subject: tags) {
			if (this.tagExists(subject)) {
				tagsLists.add(this.getTagBySubject(subject));
			}
			else {
				Tags newTag = this.createTag(new Tags(subject));
				tagsLists.add(newTag);
			}
		}
		q.setTags(tagsLists);
		return this.updateQuestion(q);
	}
	
	public List<Question> allQuestions(){
		return questionRepo.findAll();
	}
	
	public Answer createAnswer(Answer a) {
		return ansRepo.save(a);
	}
	
	public Answer updateAnswer(Answer a) {
		return ansRepo.save(a);
	}
	
	public Answer findAnswer(Long id) {
		Optional<Answer> maybeA = ansRepo.findById(id);
		if (maybeA.isPresent()) {
			return maybeA.get();
		}
		else {
			return null;
		}
	}
	
	public List<Answer> allAnswers(){
		return ansRepo.findAll();
	}
	
	public Tags createTag(Tags t) {
		return tagsRepo.save(t);
	}
	
	public Tags updateTag(Tags t) {
		return tagsRepo.save(t);
	}
	
	public Tags findTag(Long id) {
		Optional<Tags> maybeT = tagsRepo.findById(id);
		if (maybeT.isPresent()) {
			return maybeT.get();
		}
		else {
			return null;
		}
	}
	
	public List<Tags> allTags(){
		return tagsRepo.findAll();
	}
	
	public boolean tagExists(String subject) {
		return tagsRepo.existsBySubject(subject);
	}
	
	public Tags getTagBySubject(String subject) {
		Optional<Tags> tempTags = tagsRepo.findBySubject(subject);
		if (tempTags.isPresent()) {
			return tempTags.get();
		}
		else {
			return null;
		}
	}
	
	
	
}

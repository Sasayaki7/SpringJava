package com.sasayaki7.dojooverflow.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sasayaki7.dojooverflow.models.Answer;
import com.sasayaki7.dojooverflow.models.Question;
import com.sasayaki7.dojooverflow.models.Tags;
import com.sasayaki7.dojooverflow.services.ApiService;


@Controller
public class OverflowController {
	private ApiService api;
	public OverflowController(ApiService api) {
		this.api = api;
	}
	
	@RequestMapping("/questions")
	public String questionDashboard(Model model) {
		model.addAttribute("questions", api.allQuestions());
		return "questiondashboard.jsp";
	}

	
	@RequestMapping("/questions/new")
	public String questionForm(Model model) {
		model.addAttribute("question", new Question());
		return "questionform.jsp";
	}
	
	@RequestMapping(value="/questions/new", method=RequestMethod.POST)
	public String questionSubmit(@RequestParam(value="tags") String tags, @RequestParam(value="text") String text, RedirectAttributes errorMessages) {
		boolean errorLess = true;
		if(text.equals("")) {
			errorMessages.addFlashAttribute("textError", "Question must not be blank");
			errorLess = false;
		} else if(text.length() < 5) {
			errorMessages.addFlashAttribute("textError", "Question needs to be at least 5 characters");
			errorLess = false;
		}
		List<String> tagsList = Arrays.asList(tags.split(","));

		if(tags.equals("")) {
			errorLess=false;
			errorMessages.addFlashAttribute("tagsError", "Please add at least one tag");
		}
		else {
			if(tagsList.size() > 3) {
				errorMessages.addFlashAttribute("tagsError", "You may only have a max of 3 tags");
				errorLess = false;
			}
		}
		if (!errorLess) {
			errorMessages.addFlashAttribute("tagvalue", tags);
			errorMessages.addFlashAttribute("textvalue", text);
			return "redirect:/questions/new";
		}
		else {
			Question q = api.createQuestion(new Question(text));
			List<Tags> tagsLists = new ArrayList<Tags>();
			for (String subject: tagsList) {
				if (api.tagExists(subject)) {
					tagsLists.add(api.getTagBySubject(subject));
				}
				else {
					Tags newTag = api.createTag(new Tags(subject));
					tagsLists.add(newTag);
				}
			}
			q.setTags(tagsLists);
			api.updateQuestion(q);
			return "redirect:/questions";
		}
	}
	
	@RequestMapping(value="/questions/{id}")
	public String showQuestion(@PathVariable("id") Long id, Model model) {
		Question q = api.findQuestion(id);
		model.addAttribute("question", q);
		return "answerform.jsp";
	}
	
	@RequestMapping(value="/questions/{id}/answer", method=RequestMethod.POST)
	public String answerSubmit(@PathVariable("id") Long qid, @RequestParam("answer") String answer, RedirectAttributes errorAns) {
		if(answer.length() < 1) {
			errorAns.addFlashAttribute("ansError", "Answer must not be blank");
			return "redirect:/questions/"+qid;
		}
		else {
			Question q = api.findQuestion(qid);
			Answer ans = new Answer(answer);
			ans.setQuestion(q);
			api.createAnswer(ans);
			return "redirect:/questions/"+qid;
		}
	}
	
}

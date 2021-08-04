package com.sasayaki7.rowotingtest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/coding")
public class CodingController {

	@RequestMapping("")
	public String getCoding() {
		return "Hello Coding Dojo! owo";
	}
	
	@RequestMapping("/python")
	public String flask() {
		return "Python Flask was amazing owo";
	}
	
	@RequestMapping("/java")
	public String javaSpring() {
		return "Java spwingu >w< so cute >w<";
	}

}

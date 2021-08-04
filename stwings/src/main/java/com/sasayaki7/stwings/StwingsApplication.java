package com.sasayaki7.stwings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class StwingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StwingsApplication.class, args);
	}
	
	@RequestMapping("/")
	
	public String meow() {
		return "Hewwo owo are you weady to be Miku Miku >w<";
	}
	
	@RequestMapping("/random")
	public String springComment() {
		return "Spwing is nice uwu XD Isn't it awesome owo >w<";
	}
	
}

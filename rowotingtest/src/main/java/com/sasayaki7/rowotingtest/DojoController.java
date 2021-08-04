package com.sasayaki7.rowotingtest;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class DojoController {
	@RequestMapping("/{location}")
	public String showLocation(@PathVariable("location") String location) {
		if (location.equals("dojo")) {
			return "dojo is cute >w<";
		}
		else if (location.equals("burbank-dojo")) {
			return "burbank is too >w<";
		}
		else if (location.equals("san-jose")) {
			return "headquarters XDDDDD uwuwuwu";
		}
		else {
			return "I dont know this ;-;";
		}
	}
}

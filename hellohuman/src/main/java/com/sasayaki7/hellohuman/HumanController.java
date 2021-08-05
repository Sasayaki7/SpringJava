package com.sasayaki7.hellohuman;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class HumanController {
	@RequestMapping("/")
	public String greeting(@RequestParam(value="firstname", required = false) String firstname, @RequestParam(value="lastname", required = false) String lastname) {
		String returnQuery = "<a href='/'>Go back</a><br/>";
		if(firstname == null) {
			return returnQuery+"Hi human";
		}
		else {
			if (lastname==null) {
				return returnQuery+"Hi " + firstname;
			}
			else {
				return returnQuery+"Hi "+firstname+" "+lastname;
			}
		}
	}
}

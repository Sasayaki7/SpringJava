package com.sasayaki7.thecode;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CodeController {
	
	@RequestMapping("/")
	public String codeGuess() {
		return "code.jsp";
	}
	
	
	@RequestMapping(value="/submitcode", method=RequestMethod.POST)
	public String checkCode(@RequestParam(value="code") String code, RedirectAttributes redirectAttributes) {
		if (!code.equals("bushido")) {
			redirectAttributes.addFlashAttribute("flash", "You must train harder!!!!!!!!!!");
			return "redirect:/";
		}
		else {
			return "redirect:/code";
		}
	}
	
	@RequestMapping("/code")
    public String showCode() {
        return "entercode.jsp";
    }
}

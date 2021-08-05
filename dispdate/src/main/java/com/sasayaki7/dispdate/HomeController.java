package com.sasayaki7.dispdate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import java.util.Date;
import java.time.LocalTime;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String index() {
		return "displaydatehome.jsp";
	}


	@RequestMapping("/date")
	public String dateTemplate(Model model) {
		Date date = new Date();
		model.addAttribute("date", date.toString());
		return "displaydate.jsp";
	}

	@RequestMapping("/time")
	public String timeTemplate(Model model) {
		model.addAttribute("time", LocalTime.now());
		return "displaytime.jsp";
	}

}

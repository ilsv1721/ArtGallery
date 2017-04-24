package com.ilya.art.webcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomePageController {

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String getHomePage() {
		return "HomePage";
	}
	


}

package com.ilya.art.webcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginPageController {
	@RequestMapping(value = { "/login" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String getHomePage() {
		return "LoginPage";

	}

}

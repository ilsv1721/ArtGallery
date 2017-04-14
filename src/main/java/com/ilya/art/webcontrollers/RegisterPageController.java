package com.ilya.art.webcontrollers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ilya.art.domain.User;
import com.ilya.art.repositories.interfaces.UserDAO;

@Controller
@RequestMapping(value = "/register")
public class RegisterPageController {

	@Autowired
	UserDAO userDAO;

	@RequestMapping(method = GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute(new User());
		return "RegisterPage";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processRegistr(HttpServletRequest request, @Valid User user, Errors errors) {
		if (errors.hasErrors()) {
			return "RegisterPage";
		}
		userDAO.addUser(user);
		return ("redirect:/");

	}

}

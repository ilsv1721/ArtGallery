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

import com.ilya.art.dto.UserDetailsDto;
import com.ilya.art.repositories.interfaces.RoleDao;
import com.ilya.art.services.interfaces.UserService;

@Controller
@RequestMapping(value = "/register")

public class RegisterPageController {

	@Autowired
	UserService UserService;

	@Autowired
	RoleDao roleDao;

	@RequestMapping(method = GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute(new UserDetailsDto());
		return "RegisterPage";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processRegistr(HttpServletRequest request, @Valid UserDetailsDto userDetailsDto, Errors errors) {
		if (errors.hasErrors()) {
			return "RegisterPage";
		}
		UserService.registerNewUser(userDetailsDto);
		return ("redirect:/");

	}

}

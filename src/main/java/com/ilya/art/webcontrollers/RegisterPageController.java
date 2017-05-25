package com.ilya.art.webcontrollers;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ilya.art.dto.UserDetailsDto;
import com.ilya.art.repositories.interfaces.RoleDao;
import com.ilya.art.services.interfaces.UserService;

@Controller
@RequestMapping(value = "/register")

public class RegisterPageController {

	private static final Logger logger = LogManager.getLogger(RegisterPageController.class);

	@Autowired
	UserService UserService;

	@Autowired
	RoleDao roleDao;

	@RequestMapping(method = RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute(new UserDetailsDto());
		return "RegisterPage";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processRegistr(@Valid @ModelAttribute UserDetailsDto userDetailsDto, BindingResult res) {
		System.out.println("dsa");
		if (res.hasErrors()) {
			return "RegisterPage";
		} else {
			try {
				UserService.registerNewUser(userDetailsDto);
				logger.info("New user has been succesfully created for email " + userDetailsDto.getEmail());
				return "redirect:/";
			} catch (EntityExistsException ex) {
				logger.error("Can not create new user. Email " + userDetailsDto.getEmail() + " was already taken.");
				return "RegisterPage";
			}
		}
	}

}

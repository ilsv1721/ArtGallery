#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.webcontrollers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ${package}.domain.Adress;
import ${package}.domain.BalanceDetails;
import ${package}.domain.User;
import ${package}.repositories.dao.UserDao;

@Controller
@RequestMapping(value = "/register")
public class RegisterPageController {

	@Autowired
	UserDao userDAO;

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

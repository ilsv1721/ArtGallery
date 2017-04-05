#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.webcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/exhibitions")
public class ExhibitionsPageController {

	@RequestMapping
	public String deafaultExhibitionsPage() {
		return "ExhibitionsPage";
	}

}

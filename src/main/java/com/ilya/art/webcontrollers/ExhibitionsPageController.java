package com.ilya.art.webcontrollers;

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

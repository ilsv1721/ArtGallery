package com.ilya.art.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ilya.art.services.interfaces.ExhibitionService;

@Controller
@RequestMapping(value = "/exhibitions")
public class ExhibitionsPageController {

	@Autowired
	ExhibitionService exServ;

	@RequestMapping
	public String deafaultExhibitionsPage(Model model) {
		model.addAttribute("pastExhibitions", exServ.getPastExhibitions());
		model.addAttribute("currentExhibitions", exServ.getCurrentExhibitions());
		model.addAttribute("futureExhibitions", exServ.getFutureExhibitions());
		return "ExhibitionsPage";
	}

	@RequestMapping(value = "/{exhibFormatedTitle}")
	public String getConcreteExhibition(Model model, @PathVariable Long exhibFormatedTitle) {
		model.addAttribute("exhibition", exServ.findExhibition(exhibFormatedTitle));
		return "ConcreteExhibitionPage";
	}

}

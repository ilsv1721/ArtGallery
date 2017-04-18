package com.ilya.art.webcontrollers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ilya.art.dto.ExhibitionAnnounceDto;
import com.ilya.art.services.interfaces.ExhibitionService;

@Controller
@RequestMapping(value = "/panel")
public class PanelController {

	@Autowired
	ExhibitionService exhibitionService;

	@RequestMapping(method = RequestMethod.GET)
	String getDeffaultPanelView() {
		return "PanelPage";
	}

	@RequestMapping(value = "/exhibcreator", method = RequestMethod.GET)
	String getExhibitionCreationPage(Model model) {
		model.addAttribute("exhibAnounceDTO", new ExhibitionAnnounceDto());
		return "ExhibitionCreationPage";
	}

	@RequestMapping(value = "/exhibcreator", method = RequestMethod.POST)
	String process(HttpServletRequest request, @Valid ExhibitionAnnounceDto exhibAnounceDTO, Errors errors) {
		exhibitionService.anounceNewExhibition(exhibAnounceDTO);
		return ("redirect:/panel/");
	}

}

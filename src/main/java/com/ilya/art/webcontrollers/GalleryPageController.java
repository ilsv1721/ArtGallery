package com.ilya.art.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ilya.art.services.interfaces.PaintingService;

@Controller
@RequestMapping(value = "/gallery")
public class GalleryPageController {

	@Autowired
	PaintingService paintService;

	@RequestMapping(value = "/random", method = RequestMethod.GET)
	public String getRandomPaintingPage(Model model) {
		model.addAttribute("paintingDto", paintService.getRandomPainting());
		return "RandomPaintingPage";
	}
}

package com.ilya.art.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ilya.art.services.interfaces.GenreService;
import com.ilya.art.services.interfaces.PaintingService;

@Controller
@RequestMapping(value = "/paintings")
public class PaintingPageController {

	@Autowired
	PaintingService paintingService;

	@Autowired
	GenreService genreService;

	@RequestMapping(value = "/{paintid}")
	public String getPaintPage(Model model, @PathVariable Long paintid) {
		model.addAttribute("paint", paintingService.getById(paintid));
		model.addAttribute("genres", genreService.getGenreOfPainting(paintid));
		return "ConcretePaintPage";
	}
}

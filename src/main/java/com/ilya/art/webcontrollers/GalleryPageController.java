package com.ilya.art.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ilya.art.services.interfaces.ExhibitionService;
import com.ilya.art.services.interfaces.GenreService;
import com.ilya.art.services.interfaces.PaintingService;

@Controller
@RequestMapping(value = "/gallery")
public class GalleryPageController {

	@Autowired
	PaintingService paintService;

	@Autowired
	ExhibitionService exhibitionService;

	@Autowired
	GenreService genreService;

	@RequestMapping(value = "/random", method = RequestMethod.GET)
	public String getRandomPaintingPage(Model model) {
		model.addAttribute("paintingDto", paintService.getRandomPainting());
		return "RandomPaintingPage";
	}

	@RequestMapping(value = "/byExhibition")
	public String getPaintingsByExhibition(Model model) {
		model.addAttribute("exhibitions", exhibitionService.getPastExhibitionTitileAndIdDto());
		return "GalleryByExhibitionPage";
	}

	@RequestMapping(value = "/byGenre")
	public String getPaintingsByGenre(Model model) {
		model.addAttribute("genreList", genreService.getAllDto());
		return "GalleryByGenrePage";
	}

}

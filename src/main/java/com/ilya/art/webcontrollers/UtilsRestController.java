package com.ilya.art.webcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ilya.art.dto.ExhibitionTitileAndIdDto;
import com.ilya.art.dto.PaintingDto;
import com.ilya.art.services.interfaces.ExhibitionService;
import com.ilya.art.services.interfaces.GenreService;
import com.ilya.art.services.interfaces.PaintingService;
import com.ilya.art.services.interfaces.UserService;

@RestController
@RequestMapping(value = "/rest")
public class UtilsRestController {

	@Autowired
	ExhibitionService exhibitionService;

	@Autowired
	UserService userService;

	@Autowired
	PaintingService paintingService;

	@Autowired
	GenreService genreService;

	@RequestMapping(value = "/exhibitionsTitlesAndIds")
	List<ExhibitionTitileAndIdDto> getAllExhibitionsTitleAndId() {
		return exhibitionService.getAllExhibitionTitileAndIdDto();
	}

	@RequestMapping(value = "/validateUniqueEmail", method = RequestMethod.GET)
	public boolean validateEmailUnique(@RequestParam String email) {
		return userService.validateUniqueByEmail(email);
	}

	@RequestMapping(value = "/paintingsByExhibition", method = RequestMethod.GET)
	public List<PaintingDto> getPaintingsSrcByExhibitionId(@RequestParam Long exhibitionId) {
		return exhibitionService.getPaintingsOfExhibition(exhibitionId);
	}

	@RequestMapping(value = "/paintingsByGenre", method = RequestMethod.GET)
	public List<PaintingDto> getPaintingsSrcByGenreId(@RequestParam Long genreId) {
		return genreService.getAllPaintingsWithThisGenre(genreId);
	}

	@RequestMapping(value = "/validateExistEmail", method = RequestMethod.GET)
	public boolean validateEmailExist(@RequestParam String email) {
		return userService.validateExistByEmail(email);
	}

	@RequestMapping(value = "/randompainting", method = RequestMethod.GET)
	public PaintingDto getrandomPaint() {
		return paintingService.getRandomPainting();
	}
}

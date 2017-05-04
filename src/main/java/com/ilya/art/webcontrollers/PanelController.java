package com.ilya.art.webcontrollers;

import java.io.IOException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ilya.art.dto.ExhibitionEditionDto;
import com.ilya.art.dto.GenreDto;
import com.ilya.art.dto.GenreEditDto;
import com.ilya.art.dto.NewsDto;
import com.ilya.art.services.interfaces.ExhibitionService;
import com.ilya.art.services.interfaces.GenreService;
import com.ilya.art.services.interfaces.NewsService;
import com.ilya.art.utils.SimpleStringURLEncoderDecoder;

@Controller
@RequestMapping(value = "/panel")
public class PanelController {

	@Autowired
	ExhibitionService exhibitionService;

	@Autowired
	NewsService newsService;

	@Autowired
	GenreService genreService;

	@RequestMapping(method = RequestMethod.GET)
	String getDeffaultPanelView() {
		return "PanelPage";
	}

	@RequestMapping(value = "/exhibcreator", method = RequestMethod.GET)
	String getExhibitionCreationPage() {
		return "ExhibitionCreationPage";
	}

	@RequestMapping(value = "/exhibcreator", method = RequestMethod.POST)
	String process(HttpServletRequest request,
			@Valid @ModelAttribute("exhibAnounceDTO") ExhibitionEditionDto exhibEditionDTO, Errors errors)
			throws IllegalStateException, IOException {
		exhibitionService.anounceNewExhibition(exhibEditionDTO);
		return ("redirect:/panel/");
	}

	@RequestMapping(value = "/chooser", method = RequestMethod.GET)
	String getExhibitionEditionChoosePage(Model model, @RequestParam("lookfor") String lookfor,
			HttpServletRequest request) {

		switch (lookfor) {
		case "exhib":
			model.addAttribute("srcList", exhibitionService.getUrlEntityFieldAssistantMatchers());
			request.setAttribute("srcType", "exhibeditor");
			break;
		case "news":
			model.addAttribute("srcList", newsService.getUrlEntityFieldAssistantMatchers());
			request.setAttribute("srcType", "newseditor");
			break;
		}

		return "ExhibitionEditionChoosePage";
	}

	@RequestMapping(value = "/exhibeditor/{exhibFormatedTitle}", method = RequestMethod.GET)
	String getExhibitionEditionPage(@PathVariable String exhibFormatedTitle, Model model) {
		model.addAttribute("exhibEditionDTO",
				exhibitionService.getExhibitionEditionDto(SimpleStringURLEncoderDecoder.decode(exhibFormatedTitle)));
		return "ExhibitionEditionPage";
	}

	@RequestMapping(value = "/exhibeditor/{exhibFormatedTitle}", method = RequestMethod.POST)
	String exhibitionEdition(@PathVariable String exhibFormatedTitle,
			@ModelAttribute("exhibEditionDTO") ExhibitionEditionDto exhibitionEditionDto,
			RedirectAttributes redirects) {
		exhibitionService.editExhibition(exhibitionEditionDto);
		redirects.addFlashAttribute("statusOfPreviousOperation", "Success");
		return "redirect:/panel/";
	}

	@RequestMapping(value = "/deletion/del", method = RequestMethod.POST)
	public @ResponseBody String deleteExhib(@RequestParam long srcId, @RequestParam String srcType) {
		switch (srcType) {
		case "exhib":
			exhibitionService.deleteExhibition(srcId);
			break;
		case "news":
			newsService.deleteNewsById(srcId);
			break;
		}
		return "Success";
	}

	@RequestMapping(value = "/newscreate", method = RequestMethod.GET)
	public String getNewsCreationPage() {
		return "NewsCreationPage";
	}

	@RequestMapping(value = "/newscreate", method = RequestMethod.POST)
	public String createNews(@Valid @ModelAttribute("NewsDto") NewsDto newsDto, Errors errors) {
		newsService.persistNews(newsDto);
		return "redirect:/panel/";
	}

	@RequestMapping(value = "/newseditor/{newsId}", method = RequestMethod.GET)
	String getNewsEditionPage(@PathVariable long newsId, Model model) {
		model.addAttribute("NewsDto", newsService.getNewsAsNewsDtoById(newsId));
		return "NewsEditionPage";
	}

	@RequestMapping(value = "/newseditor/{newsId}", method = RequestMethod.POST)
	String newsEdition(@PathVariable long newsId, @ModelAttribute("NewsDto") NewsDto newsDto,
			RedirectAttributes redirects) {
		newsService.editNews(newsDto);
		redirects.addFlashAttribute("statusOfPreviousOperation", "Success");
		return "redirect:/panel/";
	}

	@RequestMapping(value = "/genre", method = RequestMethod.GET)
	String getGenrePanelPage(Model model) {
		model.addAttribute("GenreList", genreService.getAllGenresDto());
		return "GenrePanelPage";
	}

	@RequestMapping(value = "/addGenre", method = RequestMethod.POST)
	public @ResponseBody String addGenre(@RequestParam String genre) {
		try {
			genreService.addNewGenre(genre);
			return "New genre has been added";
		} catch (EntityExistsException ex) {
			return "Sorry, but this genre already exists";
		}
	}

	@RequestMapping(value = "/deleteGenre", method = RequestMethod.POST)
	public @ResponseBody String deleteGenre(@RequestParam String genre) {
		try {
			genreService.deleteGenre(genre);
			return "Deleted";
		} catch (EntityNotFoundException ex) {
			return "This genre does not exist";
		}
	}

	@RequestMapping(value = "/editGenre", method = RequestMethod.POST)
	public @ResponseBody String editGenre(@Valid @RequestBody GenreEditDto genreEditDto, BindingResult res) {
		if (!res.hasErrors()) {
			try {
				genreService.editGenre(genreEditDto);
				return "Changed";
			} catch (EntityNotFoundException ex) {
				return "This genre does not exist.";
			}
		} else
			return "Validation errror";
	}

}

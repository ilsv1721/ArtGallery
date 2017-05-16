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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ilya.art.dto.ExhibitionDto;
import com.ilya.art.dto.GenreDto;
import com.ilya.art.dto.GenreEditDto;
import com.ilya.art.dto.NewsDto;
import com.ilya.art.dto.PaintingDto;
import com.ilya.art.dto.PaintingMetaDto;
import com.ilya.art.dto.RoleDto;
import com.ilya.art.dto.RoleEditDto;
import com.ilya.art.services.interfaces.ExhibitionService;
import com.ilya.art.services.interfaces.GenreService;
import com.ilya.art.services.interfaces.NewsService;
import com.ilya.art.services.interfaces.PaintingService;
import com.ilya.art.services.interfaces.RoleService;
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

	@Autowired
	RoleService roleService;

	@Autowired
	PaintingService paintingService;

	@RequestMapping(method = RequestMethod.GET)
	String getDeffaultPanelView() {
		return "PanelPage";
	}

	@RequestMapping(value = "/exhibcreator", method = RequestMethod.GET)
	String getExhibitionCreationPage(Model model) {
		model.addAttribute("exhibAnounceDTO", new ExhibitionDto());
		return "ExhibitionCreationPage";
	}

	@RequestMapping(value = "/exhibcreator", method = RequestMethod.POST)
	String process(HttpServletRequest request, @Valid @ModelAttribute("exhibAnounceDTO") ExhibitionDto exhibEditionDTO,
			Errors errors) throws IllegalStateException, IOException {
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
			@ModelAttribute("exhibEditionDTO") ExhibitionDto exhibitionEditionDto, RedirectAttributes redirects) {
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

	@RequestMapping(value = "/addGenre", method = RequestMethod.POST)
	public @ResponseBody String addGenre(@Valid @RequestBody GenreDto genre, BindingResult res) {
		if (!res.hasErrors()) {
			try {
				genreService.addNewGenre(genre);
				return "New genre has been added";
			} catch (EntityExistsException ex) {
				return "Sorry, but this genre already exists";
			}
		} else
			return "Validate errors: " + res.getErrorCount();
	}

	@RequestMapping(value = "/deleteGenre", method = RequestMethod.POST)
	public @ResponseBody String deleteGenre(@Valid @RequestBody GenreDto genre, BindingResult res) {
		if (!res.hasErrors()) {
			try {
				genreService.deleteGenre(genre);
				return "Deleted";
			} catch (EntityNotFoundException ex) {
				return "This genre does not exist";
			}
		} else
			return "Validate errors: " + res.getErrorCount();
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
			return "Validate errors: " + res.getErrorCount();
	}

	@RequestMapping(value = "/genre", method = RequestMethod.GET)
	String getGenrePanelPage(Model model) {
		model.addAttribute("GenreList", genreService.getAllDto());
		return "GenrePanelPage";
	}

	@RequestMapping(value = "/role", method = RequestMethod.GET)
	String RolePanelPage(Model model) {
		model.addAttribute("RoleList", roleService.getAllDto());
		return "RolePanelPage";
	}

	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	public @ResponseBody String addRole(@Valid @RequestBody RoleDto role, BindingResult res) {
		if (!res.hasErrors()) {
			try {
				roleService.addNewRole(role);
				return "New role has been added";
			} catch (EntityExistsException ex) {
				return "Sorry, but this role already exists";
			}
		} else
			return "Validate errors: " + res.getErrorCount();
	}

	@RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
	public @ResponseBody String deleteRole(@Valid @RequestBody RoleDto role, BindingResult res) {
		if (!res.hasErrors()) {
			try {
				roleService.deleteGenre(role);
				return "Deleted";
			} catch (EntityNotFoundException ex) {
				return "This role does not exist";
			}
		} else
			return "Validate errors: " + res.getErrorCount();
	}

	@RequestMapping(value = "/editRole", method = RequestMethod.POST)
	public @ResponseBody String editRole(@Valid @RequestBody RoleEditDto roleEditDto, BindingResult res) {
		if (!res.hasErrors()) {
			try {
				roleService.editGenre(roleEditDto);
				return "Changed";
			} catch (EntityNotFoundException ex) {
				return "This role does not exist.";
			}
		} else
			return "Validate errors: " + res.getErrorCount();
	}

	@RequestMapping(value = "/paintingnew", method = RequestMethod.GET)
	public String getPaintingNewPage(Model model) {
		model.addAttribute(new PaintingDto());
		model.addAttribute("genreList", genreService.getAllDto());
		model.addAttribute("exhibitionList", exhibitionService.findAllBasicDto());
		return "NewPaintingPage";
	}

	@RequestMapping(value = "/paintingnew", method = RequestMethod.POST)
	public String processNewPaint(@Valid PaintingDto paintingDto, BindingResult binds,
			@RequestPart MultipartFile file) {
		if ((!binds.hasErrors()) && (!file.isEmpty())) {
			paintingService.addNewPainting(paintingDto, file);
		}
		return "redirect:/panel/";
	}

	@RequestMapping(value = "/paintingedit", method = RequestMethod.GET)
	public String getPaintingEditPage(Model model) {
		model.addAttribute("exhibitions", exhibitionService.getAllExhibitionTitileAndIdDto());
		model.addAttribute("paintingEditDto", new PaintingMetaDto());
		model.addAttribute("genreList", genreService.getAllDto());
		return "PaintingEditPage";
	}

	@RequestMapping(value = "/paintingedit", method = RequestMethod.POST)
	public String processPaintingEditPage(@Valid PaintingMetaDto paintingDto, BindingResult binds, Model model) {
		if (!binds.hasErrors()) {
			paintingService.editDescContent(paintingDto);
		}
		return "redirect:/panel/paintingedit";
	}

	@RequestMapping(value = "/paintingdelete", method = RequestMethod.POST)
	public @ResponseBody String editPaintingEdit(Long id) {
		paintingService.deletePainting(id);
		return "ok";
	}

}

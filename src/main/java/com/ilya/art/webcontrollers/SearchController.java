package com.ilya.art.webcontrollers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ilya.art.services.interfaces.ExhibitionService;
import com.ilya.art.services.interfaces.PaintingService;
import com.ilya.art.utils.web.UrlEntityMapper;

@Controller
@RequestMapping(value = "/search")
public class SearchController {

	@Autowired
	ExhibitionService exhibitionService;

	@Autowired
	PaintingService paintingService;

	@RequestMapping(method = RequestMethod.GET)
	public String getSearchResult(Model model, @RequestParam String searchstring) {
		String status;
		if (searchstring.length() >= 3) {
			List<UrlEntityMapper> paintsList = paintingService.findPaintingByTitleSubstring(searchstring);
			List<UrlEntityMapper> exhibsList = exhibitionService.findExhibitionByTitleSubstring(searchstring);

			if (paintsList.isEmpty() && exhibsList.isEmpty()) {
				status = "Nothing has been found";
			} else {
				status = "Results";
			}
			model.addAttribute("paintsList", paintsList);
			model.addAttribute("exhibsList", exhibsList);
		} else {
			status = "Search string must be > 3.";
		}
		model.addAttribute("status", status);
		return "SearchResultPage";
	}
}

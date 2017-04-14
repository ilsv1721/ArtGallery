package com.ilya.art.webcontrollers;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ilya.art.domain.Exhibition;
import com.ilya.art.repositories.interfaces.ExhibitionDao;

@Controller
public class HomePageController {

	@Autowired
	ServletContext servletContext;

	@Autowired
	ExhibitionDao exDao;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String getHomePage() {
		//Exhibition ex = exDao.getExhibition("The Witcher");
		//System.out.println(ex.getMedia().iterator().next());
		
		// System.out.println(servletContext.getRealPath("/WEB-INF"));
		return "HomePage";
	}

}

package com.ilya.art.webcontrollers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ilya.art.dto.NewsDto;
import com.ilya.art.services.interfaces.NewsService;

@Controller
@RequestMapping(value = "/news")
public class NewsPageController {

	@Autowired
	NewsService newsService;

	@RequestMapping
	public String getDefaultNewsPage(HttpServletRequest request) {
		List<NewsDto> descNewsList = newsService.getDescOrderedNews();
		request.setAttribute("newsList", descNewsList);
		return "NewsPage";

	}

}

package com.ilya.art.webcontrollers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ilya.art.domain.News;
import com.ilya.art.services.interfaces.NewsService;

@Controller
@RequestMapping(value = "/news")
public class NewsPageController {

	@Autowired
	NewsService newsService;

	@RequestMapping
	public String getDefaultNewsPage(HttpServletRequest request) {
		List<News> descNewsList = newsService.getDescOrderedNews();
		request.setAttribute("newsList", descNewsList);
		return "NewsPage";

	}

}

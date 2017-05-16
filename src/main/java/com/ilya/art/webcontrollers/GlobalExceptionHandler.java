package com.ilya.art.webcontrollers;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ilya.art.exceptions.NotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public String handleNotFoundException(HttpServletRequest request, Exception ex) {
		if (ex != null)
			if (ex instanceof NotFoundException) {
				logger.error(
						"NotFoundException:: Ocured while trying to get resource by URL " + request.getRequestURL());
				return "NotFoundPage";
			}
		logger.error("Exception:: Ocured while trying to get resource by URL " + request.getRequestURL());
		return "InternalServerErrorPage";
	}

}

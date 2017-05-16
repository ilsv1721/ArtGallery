package com.ilya.art.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource was not found")
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8081939374116844886L;

	public NotFoundException(String param) {
		super(param);
	}

	public NotFoundException() {

	}

}

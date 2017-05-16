package com.ilya.art.exceptions;

public class ExhibitionNotFoundException extends NotFoundException {

	private static final long serialVersionUID = -4699821778771055083L;

	public ExhibitionNotFoundException(String param) {
		super("ExhibitionNotFoundException with serarch param " + param);

	}

}

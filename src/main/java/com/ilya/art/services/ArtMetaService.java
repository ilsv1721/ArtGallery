package com.ilya.art.services;

import com.ilya.art.dto.ArtMetaDto;
import com.ilya.art.services.interfaces.UserService;

public class ArtMetaService implements com.ilya.art.services.interfaces.ArtMetaService {

	ExhibitionService exService;

	UserService userService;

	@Override
	public ArtMetaDto getSummaryOnArt() {
		return null;
	}

}

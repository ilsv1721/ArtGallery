package com.ilya.art.dto;

import com.ilya.art.domain.Exhibition;

public class ExhibitiosTitlesFormatted {
	private String title;
	private String formattedTitle;

	public static class TitleEncoderDecoder {
		static public String encode(String title) {
			return title.replaceAll("\\s", "_");
		}

		static public String decode(String formatted) {
			return formatted.replaceAll("_", " ");
		}
	}

	public ExhibitiosTitlesFormatted(String title, String clearedTitle) {
		this.title = title;
		this.formattedTitle = clearedTitle;
	}

	public ExhibitiosTitlesFormatted(Exhibition exhibition) {
		this.title = exhibition.getTitle();
		this.formattedTitle = TitleEncoderDecoder.encode(title);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFormattedTitle() {
		return formattedTitle;
	}

	public void setFormattedTitle(String formattedTitle) {
		this.formattedTitle = formattedTitle;
	}

}

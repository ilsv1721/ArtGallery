package com.ilya.art.utils;

public class SimpleCustomEncoderDecoder {

	static public String encode(String title) {
		return title.replaceAll("\\s", "_");
	}

	static public String decode(String formatted) {
		return formatted.replaceAll("_", " ");

	}
}

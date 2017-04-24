package com.ilya.art.utils;

public class SimpleStringURLEncoderDecoder {

	static public String encode(String toEncode) {
		return toEncode.replaceAll("\\s", "_");
	}

	static public String decode(String toDecode) {
		return toDecode.replaceAll("_", " ");

	}
}

package com.ilya.art.dto;

import java.io.Serializable;

import com.ilya.art.utils.SimpleStringURLEncoderDecoder;

public class UrlChosserAssistantMatcher implements Serializable {

	private static final long serialVersionUID = -3375760183780409422L;

	private String raw;
	private String formatted;

	public UrlChosserAssistantMatcher() {
	}

	public UrlChosserAssistantMatcher(String raw) {
		this.raw = raw;
		this.formatted = SimpleStringURLEncoderDecoder.encode(raw);
	}

	public String getRaw() {
		return raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

	public String getFormatted() {
		return formatted;
	}

	public void setFormatted(String formatted) {
		this.formatted = formatted;
	}

}

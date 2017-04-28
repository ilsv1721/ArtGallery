package com.ilya.art.utils.web;

import java.io.Serializable;

import com.ilya.art.utils.SimpleStringURLEncoderDecoder;

public class UrlEntityFieldAssistantMatcher implements Serializable {

	private static final long serialVersionUID = -3375760183780409422L;

	private String raw;
	private String formatted;

	public UrlEntityFieldAssistantMatcher() {
	}

	public UrlEntityFieldAssistantMatcher(String raw) {
		this.raw = raw;
		this.formatted = SimpleStringURLEncoderDecoder.encode(raw);
	}

	public UrlEntityFieldAssistantMatcher(String raw, String formatted) {
		this.raw = raw;
		this.formatted = formatted;
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

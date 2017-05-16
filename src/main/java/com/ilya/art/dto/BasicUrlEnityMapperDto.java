package com.ilya.art.dto;

import java.io.Serializable;

import com.ilya.art.utils.SimpleStringURLEncoderDecoder;
import com.ilya.art.utils.web.UrlEntityMapper;

public class BasicUrlEnityMapperDto implements Serializable, UrlEntityMapper {

	private static final long serialVersionUID = -3375760183780409422L;

	private String meta;
	private String url;

	public BasicUrlEnityMapperDto() {
	}

	public BasicUrlEnityMapperDto(String meta) {
		this.meta = meta;
		this.url = SimpleStringURLEncoderDecoder.encode(meta);
	}

	public BasicUrlEnityMapperDto(String raw, String formatted) {
		this.meta = raw;
		this.url = formatted;
	}

	@Override
	public String getMeta() {
		return meta;
	}

	@Override
	public String getUrl() {
		return url;
	}

}

package com.ilya.art.dto;

import java.io.Serializable;

import com.ilya.art.utils.SimpleStringURLEncoderDecoder;
import com.ilya.art.utils.web.UrlEntityMapper;

/**
 * Base class for mapping model entities with web URL's.
 * 
 * @author ilya
 *
 */
public class BasicUrlEnityMapperDto implements Serializable, UrlEntityMapper {

	private static final long serialVersionUID = -3375760183780409422L;

	/**
	 * Entity meta data that will be displayed to the user.
	 */
	private String meta;

	/**
	 * URL of the resource.
	 */
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((meta == null) ? 0 : meta.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasicUrlEnityMapperDto other = (BasicUrlEnityMapperDto) obj;
		if (meta == null) {
			if (other.meta != null)
				return false;
		} else if (!meta.equals(other.meta))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

}

package com.ilya.art.dto;

import com.ilya.art.domain.Painting;

public class PaintingDto extends PaintingMetaDto {

	private static final long serialVersionUID = 5704374056044725084L;

	private String path;

	public PaintingDto() {
	}

	public PaintingDto(Painting painting) {
		super(painting);
		this.path = painting.getPath();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
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
		PaintingDto other = (PaintingDto) obj;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}

}

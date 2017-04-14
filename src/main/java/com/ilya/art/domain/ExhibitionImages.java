package com.ilya.art.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "exhibitions_images")
public class ExhibitionImages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "media_id")
	private long id;

	@Column(name = "path")
	private String path;

	@ManyToOne
	@JoinColumn(name = "exhibition_id")
	private Exhibition exhibition;

	public ExhibitionImages() {
	}

	public ExhibitionImages(String path, Exhibition exhibition) {
		super();
		this.path = path;
		this.exhibition = exhibition;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exhibition == null) ? 0 : exhibition.hashCode());
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
		ExhibitionImages other = (ExhibitionImages) obj;
		if (exhibition == null) {
			if (other.exhibition != null)
				return false;
		} else if (!exhibition.equals(other.exhibition))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return path;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Exhibition getExhibition() {
		return exhibition;
	}

	public void setExhibition(Exhibition exhibition) {
		this.exhibition = exhibition;
	}

}

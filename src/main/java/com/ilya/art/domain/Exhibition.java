package com.ilya.art.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "exhibitions")
@NamedQueries({
		@NamedQuery(name = "Exhibition.findByTitle", query = "from Exhibition exb where exb.title= :searchedTitle"),
		@NamedQuery(name = "Exhibition.findAll", query = "from Exhibition exb") })
public class Exhibition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exhibition_id")
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date starts = new Date();

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date ends = new Date();

	@Column(name = "description")
	private String description;

	@Column(name = "title")
	private String title;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "announced_by")
	private User announcedBy;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "exhibition", fetch = FetchType.EAGER)
	private Set<ExhibitionImages> exhibitionImages = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "exhibition")
	private Set<Painting> paintings = new HashSet<>();

	public Exhibition() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getStarts() {
		return starts;
	}

	public void setStarts(Date starts) {
		this.starts = starts;
	}

	public Date getEnds() {
		return ends;
	}

	public void setEnds(Date ends) {
		this.ends = ends;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getAnnouncedBy() {
		return announcedBy;
	}

	public void setAnnouncedBy(User announcedBy) {
		this.announcedBy = announcedBy;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Painting> getPaintings() {
		return paintings;
	}

	public void setPaintings(Set<Painting> paintings) {
		this.paintings = paintings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Exhibition other = (Exhibition) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public Set<ExhibitionImages> getExhibitionImages() {
		return exhibitionImages;
	}

	public void setExhibitionImages(Set<ExhibitionImages> exhibitionImages) {
		this.exhibitionImages = exhibitionImages;
	}

}

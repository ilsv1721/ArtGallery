package com.ilya.art.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "genres")
@NamedQueries({ @NamedQuery(name = "findByGenre", query = "from Genre gen where gen.genre=:genre_search"),
		@NamedQuery(name = "findAll", query = "from Genre gen") })
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "genre_id")
	private long id;

	@Column(name = "genre")
	private String genre;

	@ManyToMany(mappedBy = "genre")
	private Set<Painting> paintingsWithThisGenre = new HashSet<>();

	public Genre() {
	}

	public Genre(String genre) {
		this.genre = genre;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Set<Painting> getPaintingsWithThisGenre() {
		return paintingsWithThisGenre;
	}

	public void setPaintingsWithThisGenre(Set<Painting> paintingsWithThisGenre) {
		this.paintingsWithThisGenre = paintingsWithThisGenre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
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
		Genre other = (Genre) obj;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Genre [genre=" + genre + "]";
	}

}

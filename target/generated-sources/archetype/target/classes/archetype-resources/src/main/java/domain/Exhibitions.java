#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


public class Exhibitions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exhibition_id")
	private int id;

	@Column(name = "st${artifactId}_datetime")
	private Date st${artifactId}s = new Date();

	@Column(name = "end_datetime")
	private Date ends = new Date();

	@Column(name = "description")
	private String description;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "announcedBy")
	private User announcedBy;

	private Set<String> media = new HashSet<>();

	public Exhibitions() {
	}

	public Exhibitions(User announcedBy, String description) {
		this.announcedBy = announcedBy;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getSt${artifactId}s() {
		return st${artifactId}s;
	}

	public void setSt${artifactId}s(Date st${artifactId}s) {
		this.st${artifactId}s = st${artifactId}s;
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

}

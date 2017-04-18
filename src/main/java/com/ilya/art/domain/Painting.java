package com.ilya.art.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Painting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "painting_id")
	private int id;

	@Column(name = "description")
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name = "create_date")
	private Date createDate = new Date();

	@Column(name = "filepath")
	private String filepath;

	@Column(name = "title")
	private String title;

	@OneToMany
	private User creator;

	@ManyToMany
	Set<Exhibition> exhibitiohs = new HashSet<>();

	@ManyToMany
	Set<Genre> genres = new HashSet<>();

	public Painting() {
	}

}

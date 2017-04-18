package com.ilya.art.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "news")
@NamedQueries({ @NamedQuery(name = "ascByDate", query = "from News n order by n.publishDate ASC"),
		@NamedQuery(name = "descByDate", query = "from News n order by n.publishDate DESC"),
		@NamedQuery(name = "findByTitle", query = "from News n where n.title= :searchedTitle") })
public class News {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "news_id")
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "publish_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date publishDate = new Date();

	@Column(name = "content")
	private String content;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User writtenBy;

	public News() {
	}

	public News(String titile, String content, User author) {
		this.content = content;
		this.title = titile;
		this.writtenBy = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getWrittenBy() {
		return writtenBy;
	}

	public void setWrittenBy(User writtenBy) {
		this.writtenBy = writtenBy;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

}

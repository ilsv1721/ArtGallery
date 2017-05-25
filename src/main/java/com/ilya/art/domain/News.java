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
	private long id;

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((publishDate == null) ? 0 : publishDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((writtenBy == null) ? 0 : writtenBy.hashCode());
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
		News other = (News) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (publishDate == null) {
			if (other.publishDate != null)
				return false;
		} else if (!publishDate.equals(other.publishDate))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (writtenBy == null) {
			if (other.writtenBy != null)
				return false;
		} else if (!writtenBy.equals(other.writtenBy))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "News [title=" + title + ", publishDate=" + publishDate + ", content=" + content + ", writtenBy="
				+ writtenBy + "]";
	}
	
	

}

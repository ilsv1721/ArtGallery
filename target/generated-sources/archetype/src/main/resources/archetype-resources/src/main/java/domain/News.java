#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain;

public class News {

	private int id;

	private String topic;

	private String text;

	private User writtenBy;

	public News() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getWrittenBy() {
		return writtenBy;
	}

	public void setWrittenBy(User writtenBy) {
		this.writtenBy = writtenBy;
	}

}

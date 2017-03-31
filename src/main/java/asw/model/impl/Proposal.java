package asw.model.impl;

import java.util.HashSet;
import java.util.Set;

import asw.model.Commentable;
import asw.model.types.Topic;

public class Proposal implements Commentable{

	private String title;
	private String description;
	private Topic topic;
	
	private long id;
	
	private User user;
	
	private Set<Vote> votes = new HashSet<>();
	
	Proposal(){}
	
	public Proposal(User user, String tit, String desc){
		this.user = user;
		this.title = tit;
		this.description = desc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public User getUser() {
		return user;
	}

	public Set<Vote> getVotes() {
		return votes;
	}

	public long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Proposal other = (Proposal) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Proposal [title=" + title + ", description=" + description + ", topic=" + topic + ", id=" + id
				+ ", user=" + user + ", votes=" + votes + "]";
	}
	
}

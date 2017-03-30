package model.impl;

import java.util.HashSet;
import java.util.Set;

import model.Commentable;
import model.types.Topic;

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

	
}

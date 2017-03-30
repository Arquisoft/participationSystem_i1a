package model.impl;

import model.Commentable;

public class Comment implements Commentable{
	
	private String content;
	
	private User user;
	
	private Long id;
	
	public Comment(User user, String content){
		this.content = content;
		this.user = user;
	}

	
}

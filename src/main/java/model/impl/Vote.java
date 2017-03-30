package model.impl;

public class Vote {
	
	private Proposal proposal;
	private User user;
	
	public Vote(Proposal proposal, User user){
		this.proposal = proposal;
		this.user = user;
	}

}

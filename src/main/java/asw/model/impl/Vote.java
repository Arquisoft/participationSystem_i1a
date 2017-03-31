package asw.model.impl;

public class Vote {
	
	private Proposal proposal;
	private User user;
	
	public Vote(Proposal proposal, User user){
		this.proposal = proposal;
		this.user = user;
	}

	public Proposal getProposal() {
		return proposal;
	}

	public User getUser() {
		return user;
	}

}

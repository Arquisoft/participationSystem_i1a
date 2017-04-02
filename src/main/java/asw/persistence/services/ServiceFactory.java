package asw.persistence.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceFactory {

	@Autowired
	private UserService userService;
	@Autowired
	private ProposalService proposalService;
	@Autowired
	private CommentService commentService;
	
	
	public UserService getUserService() {
		if(userService==null)
			userService = new UserService();
		return userService;
	}

	public CommentService getCommentService() {
		if(commentService==null)
			commentService = new CommentService();
		return commentService;
	}
	
	public ProposalService getProposalService() {
		if(proposalService==null)
			proposalService = new ProposalService();
		return proposalService;
	}
}

package asw.persistence.services;

import java.util.List;

import asw.model.impl.Comment;
import asw.model.impl.Proposal;
import asw.model.impl.User;

public interface CommentService {
	
	public Comment save(Comment comment);
	public boolean checkExists(Long id);
	public void delete(Comment comment);
	
	public List<Comment> findAll();
	public Comment findById(Long id);
	public List<Comment> findByUser(User user);
	public List<Comment> findByProposal(Proposal proposal);
	public Comment findByProposalAndId(Long proposalId, Long id);
	
	public void updateComment(Long proposalId, Comment comment);
	public void clearTable();
	
	
}

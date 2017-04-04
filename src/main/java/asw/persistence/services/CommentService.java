package asw.persistence.services;

import java.util.List;

import asw.model.impl.Comment;
import asw.model.impl.Proposal;
import asw.model.impl.User;

public interface CommentService {
	
	public void save(Comment comment);
	public boolean checkExists(Long id);
	public void delete(Comment comment);
	
	public List<Comment> findAll();
	public List<Comment> findByUser(User user);
	public List<Comment> findByProposal(Proposal proposal);
	
}

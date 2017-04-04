package asw.persistence.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.model.impl.*;
import asw.persistence.repositories.CommentRepository;
import asw.persistence.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository repo;
	
	@Autowired
	public CommentServiceImpl(CommentRepository repository) {
		this.repo = repository;
	}
	
	@Override
	public void save(Comment comment) {
		repo.save(comment);
	}

	@Override
	public boolean checkExists(Long id) {
		return repo.findOne(id) != null;
	}

	@Override
	public void delete(Comment comment) {
		repo.delete(comment);
	}

	@Override
	public List<Comment> findAll() {
		List<Comment> comments = new ArrayList<Comment>();
		if (repo.findAll() != null) {
			Iterator<Comment> it = repo.findAll().iterator();
			while (it.hasNext())
				comments.add(it.next());
		}
		return comments;
		//return repo.findAll();
	}

	@Override
	public List<Comment> findByUser(User user) {
		return repo.findByUser(user);
	}

	@Override
	public List<Comment> findByProposal(Proposal proposal) {
		return repo.findByProposal(proposal);
	}

	@Override
	public Comment findByProposalAndId(String proposalId, String id) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public void updateComment(String proposalId, String id) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public void insertComment(Comment comment) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not yet implemented");
	}

}

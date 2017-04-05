package asw.persistence.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.model.impl.*;
import asw.persistence.repositories.CommentRepository;
import asw.persistence.repositories.ProposalRepository;
import asw.persistence.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository repo;
	private ProposalRepository proposalRepo;
	
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
	public Comment findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public Comment findByProposalAndId(Long proposalId, Long id) throws Exception {
		Proposal p = proposalRepo.findById(proposalId);
		Comment c = repo.findById(id);
		if(!p.getComments().contains(c))
			throw new Exception("The proposal does not contain the specified comment");
		return c;
	}

	@Override
	public void updateComment(Long proposalId, Comment comment) {
		Proposal prop = proposalRepo.findById(proposalId);
		prop.getComments().add(comment);
		proposalRepo.save(prop);
	}

	@Override
	public void clearTable() {
		repo.deleteAll();
	}

}

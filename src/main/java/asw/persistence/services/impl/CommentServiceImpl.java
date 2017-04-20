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
	public CommentServiceImpl(CommentRepository repository, ProposalRepository proposalRepo) {
		this.repo = repository;
		this.proposalRepo = proposalRepo;
	}
	
	@Override
	public Comment save(Comment comment) {
		return repo.save(comment);
	}

	@Override
	public boolean checkExists(Long id) {
		return repo.exists(id);
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
		List<Comment> list = new ArrayList<Comment>();
		for( Comment c: repo.findAll()){
			if(c.getUser().equals(user)){
				list.add(c);
			}
		}
		return list;
	}

	@Override
	public List<Comment> findByProposal(Proposal proposal) {
		List<Comment> list = new ArrayList<Comment>();
		for( Comment c: repo.findAll()){
			if(c.getProposal().equals(proposal)){
				list.add(c);
			}
		}
		return list;
	}
	
	@Override
	public Comment findById(Long id) {
		return repo.findOne(id);
	}

	@Override
	public Comment findByProposalAndId(Long proposalId, Long id) {
		Proposal p = proposalRepo.findOne(proposalId);
		Comment c = repo.findOne(id);
		if(!p.getComments().contains(c))
			throw new IllegalStateException("The proposal does not contain the specified comment");
		return c;
	}

	@Override
	public void updateComment(Long proposalId, Comment comment) {
		Proposal prop = proposalRepo.findOne(proposalId);
		prop.getComments().add(comment);
		proposalRepo.save(prop);
	}

	@Override
	public void clearTable() {
		repo.deleteAll();
	}

}

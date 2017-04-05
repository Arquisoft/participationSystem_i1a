package asw.persistence.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import asw.model.impl.Association;
import asw.model.impl.Proposal;
import asw.model.impl.User;
import asw.model.impl.Vote;
import asw.persistence.repositories.ProposalRepository;
import asw.persistence.repositories.UserRepository;
import asw.persistence.repositories.VoteRepository;
import asw.persistence.services.ProposalService;
import asw.producers.VoteNotifier;

@Service
public class ProposalServiceImpl implements ProposalService {

	private ProposalRepository repo;
	private UserRepository userRepo;
	private VoteRepository voteRepo;

	@Autowired
	public ProposalServiceImpl(ProposalRepository repository,
			UserRepository userRepo,
			VoteRepository voteRepo) {
		this.repo = repository;
		this.userRepo = userRepo;
		this.voteRepo = voteRepo;
	}

	@Override
	public void save(Proposal proposal) {
		repo.save(proposal);
	}

	@Override
	public boolean checkExists(Long id) {
		return repo.findOne(id) != null;
	}

	@Override
	public void delete(Proposal proposal) {
		repo.delete(proposal);
	}

	@Override
	public List<Proposal> findAll() {
		List<Proposal> proposals = new ArrayList<Proposal>();
		if (repo.findAll() != null) {
			Iterator<Proposal> it = repo.findAll().iterator();
			while (it.hasNext())
				proposals.add(it.next());
		}
		return proposals;
		// return repo.findAll();
	}

	@Override
	public Proposal findByTitle(String tit) {
		return repo.findByTitle(tit);
	}

	@Override
	public void vote(Proposal proposal, Vote vote, User user) {
		// I dont know how to correctly save a new vote. 
		throw new RuntimeException("Not yet implemented");
		
//		voteRepo.save(vote);
//		userRepo.save(user);
//		repo.save(proposal);
//		new VoteNotifier().notifyNewVote(vote);
	}

	@Override
	public Proposal findProposalById(String id) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public void updateProposal(Proposal prop) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public void insertProposal(Proposal proposal) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not yet implemented");
	}

}

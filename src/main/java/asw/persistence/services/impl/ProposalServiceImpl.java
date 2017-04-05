package asw.persistence.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.model.impl.Proposal;
import asw.persistence.repositories.ProposalRepository;
import asw.persistence.repositories.UserRepository;
import asw.persistence.repositories.VoteRepository;
import asw.persistence.services.ProposalService;

@Service
public class ProposalServiceImpl implements ProposalService {

	private ProposalRepository repo;
	@Autowired
	public ProposalServiceImpl(ProposalRepository repository,
			UserRepository userRepo,
			VoteRepository voteRepo) {
		this.repo = repository;
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
	}

	@Override
	public Proposal findByTitle(String tit) {
		return repo.findByTitle(tit);
	}

	@Override
	public Proposal findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public void updateProposal(Proposal prop) {
		repo.save(prop);
	}
	
	@Override
	public void clearTable() {
		repo.deleteAll();
	}

}

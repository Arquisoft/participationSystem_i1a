package asw.persistence.services;

import java.util.List;

import asw.model.impl.Proposal;
import asw.model.impl.User;
import asw.model.impl.Vote;

public interface ProposalService {
	
	public void save(Proposal proposal);
	public boolean checkExists(Long id);
	public void delete(Proposal proposal);
	
	public List<Proposal> findAll();
	public Proposal findByTitle(String tit);
	
	public void vote(Proposal proposal, Vote vote, User user);
}

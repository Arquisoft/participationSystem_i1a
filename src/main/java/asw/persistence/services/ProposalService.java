package asw.persistence.services;

import java.util.List;

import asw.model.impl.Proposal;

public interface ProposalService {
	
	public void save(Proposal proposal);
	public boolean checkExists(Long id);
	public void delete(Proposal proposal);
	
	public List<Proposal> findAll();
	public Proposal findByTitle(String tit);
	public Proposal findById(Long id);
	
	public void updateProposal(Proposal prop);
	
}

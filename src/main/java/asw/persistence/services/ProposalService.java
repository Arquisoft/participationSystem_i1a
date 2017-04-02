package asw.persistence.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.model.impl.Proposal;
import asw.model.impl.User;
import asw.persistence.repositories.ProposalRepository;

@Service
public class ProposalService {

	@Autowired
    private ProposalRepository pR;
	List<Proposal> props;
	
	public void createProposal(Proposal proposal) {
        pR.insert(proposal);
    }

    public void delete(Proposal proposal) {
        pR.delete(proposal);
    }
    
    public void vote(Proposal proposal) {
        User votant = null; //TODO: Coger el usuario logueado en la aplicacion
        if (!proposal.getUserVotes().contains(votant)) {
            proposal.vote(votant);
            pR.save(proposal);
        }
    }

	public List<Proposal> getAllProposals() {
		return pR.findAll();
	}
	
	public Proposal getProposalByTitle(String tit){
		props = getAllProposals();
		for(Proposal p : props){
			if(p.getTitle().equals(tit)){
				return p;
			}
		}
		return null;
	}
	
}

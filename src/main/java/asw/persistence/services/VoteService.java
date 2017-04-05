package asw.persistence.services;

import java.util.List;

import asw.model.impl.User;
import asw.model.impl.Votable;
import asw.model.impl.Vote;

public interface VoteService {

	public void save(Vote v);
	public boolean checkExists(Long id);
	
	public List<Vote> findAll();
	public List<Vote> findByUser(User user);
	public List<Vote> findByVotable(Votable v);
	
	public void clearTable();
}

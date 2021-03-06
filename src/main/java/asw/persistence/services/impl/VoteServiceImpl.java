package asw.persistence.services.impl;

import java.security.Key;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import asw.model.impl.Proposal;
import asw.model.types.KeyVote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.model.impl.User;
import asw.model.impl.Votable;
import asw.model.impl.Vote;
import asw.persistence.repositories.VoteRepository;
import asw.persistence.services.VoteService;

@Service
public class VoteServiceImpl implements VoteService {

	private VoteRepository repo;
	
	@Autowired
	public VoteServiceImpl(VoteRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public Vote save(Vote v) {
	    return repo.save(v);
	}

	@Override
	public boolean checkExists(Long id) {
		return repo.exists(id);
	}

	@Override
	public List<Vote> findAll() {
		List<Vote> votes = new ArrayList<Vote>();
		if (repo.findAll() != null) {
			Iterator<Vote> it = repo.findAll().iterator();
			while (it.hasNext())
				votes.add(it.next());
		}
		return votes;
	}

	@Override
	public List<Vote> findVoteByUser(User user) {
		return repo.findByUser(user);
	}

	@Override
	public List<Vote> findVoteByVotable(Votable v) {
		return repo.findByVotable(v);
	}

    @Override
    public Vote findVoteByUserByVotable(User loggedinUser, Votable v) {
        return repo.findByUserAndVotable(loggedinUser, v);
    }

    @Override
    public void deleteVote(Vote v){
	    repo.delete(v);
    }

    @Override
	public void clearTable() {
		repo.deleteAll();
	}

}

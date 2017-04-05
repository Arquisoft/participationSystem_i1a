package asw.persistence.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import asw.model.impl.User;
import asw.model.impl.Votable;
import asw.model.impl.Vote;
import asw.persistence.repositories.VoteRepository;
import asw.persistence.services.VoteService;

public class VoteServiceImpl implements VoteService {

	private VoteRepository repo;
	
	@Autowired
	public VoteServiceImpl(VoteRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public void save(Vote v) {
		repo.save(v);
	}

	@Override
	public boolean checkExists(Long id) {
		return repo.findOne(id) != null;
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
	public List<Vote> findByUser(User user) {
		return repo.findByUser(user);
	}

	@Override
	public List<Vote> findByVotable(Votable v) {
		return repo.findByVotable(v);
	}

}

package asw.persistence.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.model.impl.User;
import asw.model.impl.Votable;
import asw.model.impl.Vote;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {

	List<Vote> findByUser(User user);
	List<Vote> findByVotable(Votable v);
	
}

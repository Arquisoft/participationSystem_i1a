package asw.persistence.repositories;

import java.util.List;

import asw.model.types.KeyVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.model.impl.User;
import asw.model.impl.Votable;
import asw.model.impl.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

	List<Vote> findByUser(User user);
	List<Vote> findByVotable(Votable v);
	
}

package asw.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.model.impl.Vote;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {

}

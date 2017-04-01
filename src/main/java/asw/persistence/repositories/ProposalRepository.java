package asw.persistence.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import asw.model.impl.Proposal;

@Repository
public interface ProposalRepository extends MongoRepository<Proposal, ObjectId>{
	
	//Methods
	
}

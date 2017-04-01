package asw.persistence.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import asw.model.impl.User;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId>{

	//Methods
	
}

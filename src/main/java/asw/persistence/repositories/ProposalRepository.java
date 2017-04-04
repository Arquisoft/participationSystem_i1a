package asw.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import asw.model.impl.Proposal;
import asw.model.impl.User;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long>{
	
	public Proposal findByUser(User u);
	public Proposal findByTitle(String title);
}

package asw.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import asw.model.impl.Proposal;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long>{

	Proposal findByTitle(String tit);
	
}

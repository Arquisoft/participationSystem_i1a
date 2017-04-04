package asw.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import asw.model.impl.Comment;
import asw.model.impl.Proposal;
import asw.model.impl.User;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	public List<Comment> findByProposal(Proposal p);
	public List<Comment> findByUser(User u);
}

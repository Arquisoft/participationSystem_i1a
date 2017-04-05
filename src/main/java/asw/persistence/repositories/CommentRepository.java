package asw.persistence.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import asw.model.impl.Comment;

@EnableAutoConfiguration
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {	
}

package asw.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import asw.model.impl.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public User findById(Long id);
	public User findByEmail(String email);
}

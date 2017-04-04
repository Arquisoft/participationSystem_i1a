package asw.persistence.services;

import java.util.List;

import asw.model.impl.User;

public interface UserService {

	public void save(User user);
	public boolean checkExists(Long id);
	public void delete(User user);
	
	public List<User> findAll();
	
}

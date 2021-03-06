package asw.persistence.services;

import java.util.List;

import asw.model.impl.User;

public interface UserService {

	public User save(User user);
	public boolean checkExists(Long id);
	public void delete(User user);
	
	public List<User> findAll();
	public User findById(Long id);
	
	public User findUserByLogin(String login);
	public void clearTable();
	public User findUserByLoginAndPassword(String login, String password);
	
}

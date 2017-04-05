package asw.persistence.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.model.impl.User;
import asw.persistence.repositories.UserRepository;
import asw.persistence.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository repo;
	
	@Autowired
	public UserServiceImpl(UserRepository repository) {
		this.repo = repository;
	}
	
	@Override
	public void save(User user) {
		repo.save(user);
	}

	@Override
	public boolean checkExists(Long id) {
		return repo.findOne(id) != null;
	}

	@Override
	public void delete(User user) {
		repo.delete(user);
	}

	@Override
	public User findById(Long id) {
		return repo.findById(id);
	}
	
	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		if (repo.findAll() != null) {
			Iterator<User> it = repo.findAll().iterator();
			while (it.hasNext())
				users.add(it.next());
		}
		return users;
	}

	@Override
	public User findUserByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

}

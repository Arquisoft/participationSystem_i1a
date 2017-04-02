package asw.persistence.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.model.impl.User;
import asw.persistence.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository uR;
	
	public List<User> getAllUsers(){
		return uR.findAll();
	}
}

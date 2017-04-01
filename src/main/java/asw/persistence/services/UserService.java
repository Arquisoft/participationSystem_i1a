package asw.persistence.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.persistence.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository uR;
	
}

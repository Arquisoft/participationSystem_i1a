package asw.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import asw.model.impl.User;
import asw.persistence.services.UserService;

public class UserLoginService implements UserDetailsService {

	private UserService us;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		User user = us.findUserByLogin(login);
		if(user!=null){
			return new MyUserDetails(user);
		}
		throw new UsernameNotFoundException("Username not found");
	}

}

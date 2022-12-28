package cuisines.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cuisines.Customer;
import cuisines.data.UserRepository;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService{

	private UserRepository userRepo;
	
	@Autowired
	public UserRepositoryUserDetailsService(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Customer user = userRepo.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("User "+username+" not Found");
		}
		return user;
	}

}

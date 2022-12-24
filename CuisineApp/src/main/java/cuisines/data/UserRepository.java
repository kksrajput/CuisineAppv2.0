package cuisines.data;

import org.springframework.data.repository.CrudRepository;

import cuisines.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	User findByUsername(String username);
}

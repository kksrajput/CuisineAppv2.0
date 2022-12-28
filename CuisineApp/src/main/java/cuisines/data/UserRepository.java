package cuisines.data;

import org.springframework.data.repository.CrudRepository;

import cuisines.Customer;

public interface UserRepository extends CrudRepository<Customer, Long>{
	
	Customer findByUsername(String username);
}

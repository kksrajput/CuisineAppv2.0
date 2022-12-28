package cuisines.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import cuisines.Customer;
import lombok.Data;

@Data
public class RegistrationForm {
	
	private String username;
	private String password;
	private String fullname;
	private String city;
	private String street;
	private String state;
	private String zip;
	private String phone;
	
	public Customer toUser(PasswordEncoder passwordEncoder) {
		return new Customer(
				username, passwordEncoder.encode(password),
				fullname, street, city, state, zip, phone
				);
	}
}

package cuisines.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		 final String[] userAccessedPages = {"/design","order"};
			 
		 http
			.authorizeRequests()//user Accessed Pages for the app
				.antMatchers(userAccessedPages).access("hasRole('USER')")
					.antMatchers("/","/**").access("permitAll")
					
			.and()  // login
				.formLogin()
					.loginPage("/login")
					
			.and() //logout
				.logout()
					.logoutSuccessUrl("/")
					
			.and() //h2 console non-secured
				.csrf()
					.ignoringAntMatchers("/h2/**");
					
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(encoder());
		
	}
}

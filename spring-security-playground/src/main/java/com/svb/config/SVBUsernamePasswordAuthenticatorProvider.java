package com.svb.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.svb.model.Authority;
import com.svb.model.Customer;
import com.svb.repository.CustomerRepository;

@Component
public class SVBUsernamePasswordAuthenticatorProvider implements AuthenticationProvider{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//		Businees Logic 
//		Username and Password
//		It can be Fingerprint, Iris, OTP 
		
		System.out.println("-------------- Custom Authentication Provider is getting used --------");
		String username=authentication.getName();
		String password=authentication.getCredentials().toString();
		
		List<Customer> customersList=customerRepository.findByEmail(username);
		
		if(!customersList.isEmpty()) {
			Customer c=customersList.get(0);
			Set<Authority> authorities=c.getAuthorities();
			if(passwordEncoder.matches(password, c.getPwd())) {
				List<GrantedAuthority> grantedAuthorities=getGrantedAuthorities(authorities);
				
				return new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities);
			}
			else {
				throw new BadCredentialsException("Invalid Password!!");
			}
		}
		else {
			throw new BadCredentialsException("No user registered with given username");
		}
		
		
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities){
		List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
		
		for(Authority authority:authorities) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
		}
		
		return grantedAuthorities;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	

}

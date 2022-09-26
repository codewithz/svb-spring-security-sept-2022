package com.svb.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.svb.model.Customer;
import com.svb.model.SecurityCustomer;
import com.svb.repository.CustomerRepository;

@Service
public class SVBUserDetails implements UserDetailsService{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Customer> customerList=customerRepository.findByEmail(username);
		
		if(!customerList.isEmpty()) {
			SecurityCustomer customer=new SecurityCustomer(customerList.get(0));
			return customer;
		}
		else {
			throw new UsernameNotFoundException("User details not found for "+username);
		}
	}

}

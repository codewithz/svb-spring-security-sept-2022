package com.svb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

	/*
	 * /myAccount - Secured
	 * /myBalance - Secured
	 * /myCards - Secured
	 * /myLoans - Secured
	 * /notices - NOT Secured
	 * /contact - NOT Secured
	 * */
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
//		Default Implementation
		
//		http
//			.authorizeRequests()
//			.anyRequest().authenticated()
//			.and()
//			.formLogin()
//			.and()
//			.httpBasic();
		
		http
		.authorizeRequests()
		.antMatchers("/myAccount").authenticated()
		.antMatchers("/myBalance").authenticated()
		.antMatchers("/myLoans").authenticated()
		.antMatchers("/myCards").authenticated()
		.antMatchers("/notices").permitAll()
		.antMatchers("/contact").permitAll()
		.and()
		.formLogin()
		.and()
		.httpBasic();

		
	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth.inMemoryAuthentication()
//			.withUser("admin").password("123456").authorities("admin")
//			.and()
//			.withUser("user").password("12345").authorities("read")
//			.and()
//			.passwordEncoder(NoOpPasswordEncoder.getInstance());
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
	
		InMemoryUserDetailsManager userDetailsManager=new InMemoryUserDetailsManager();
		UserDetails user1=User.withUsername("admin").password("123456").authorities("admin").build();
		UserDetails user2=User.withUsername("user").password("12345").authorities("read").build();
		
		userDetailsManager.createUser(user1);
		userDetailsManager.createUser(user2);
		
		auth.userDetailsService(userDetailsManager);
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	
	
	
}

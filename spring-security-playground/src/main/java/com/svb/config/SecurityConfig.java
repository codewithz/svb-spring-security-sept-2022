package com.svb.config;



import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.svb.filter.AuthoritiesLoggingAfterFilter;
import com.svb.filter.JWTTokenGeneratorFilter;
import com.svb.filter.JWTTokenValidatorFilter;
import com.svb.filter.RequestValidationBeforeFilter;

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
		.cors()
		.configurationSource(new CorsConfigurationSource() {
			
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration config=new CorsConfiguration();
				config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
				config.setAllowedMethods(Collections.singletonList("*")); //GET,POST,DELETE,PUT
				config.setAllowCredentials(true);
				config.setAllowedHeaders(Collections.singletonList("*"));
				config.setMaxAge(3600L);
				return config;
			}
		})
		.and()
		.csrf()
		.ignoringAntMatchers("/contact")
		.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		.and()
		.addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
		.addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
		.addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
		.addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
		.authorizeRequests()
		.antMatchers("/myAccount").hasRole("ADMIN")
		.antMatchers("/myBalance").hasAnyRole("USER","ADMIN")
		.antMatchers("/myLoans").authenticated()
		.antMatchers("/myCards").hasRole("USER")
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
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//	
//		InMemoryUserDetailsManager userDetailsManager=new InMemoryUserDetailsManager();
//		UserDetails user1=User.withUsername("admin").password("123456").authorities("admin").build();
//		UserDetails user2=User.withUsername("user").password("12345").authorities("read").build();
//		
//		userDetailsManager.createUser(user1);
//		userDetailsManager.createUser(user2);
//		
//		auth.userDetailsService(userDetailsManager);
//		
//	}
	
//	@Bean
//	public UserDetailsService userDetailsService(DataSource dataSource) {
//			return new JdbcUserDetailsManager(dataSource);
//	}
	
//	NoOpPasswordEncoder
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	
}

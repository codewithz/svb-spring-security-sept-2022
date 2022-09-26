package com.svb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity(debug=true)
public class SpringSecurityPlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityPlaygroundApplication.class, args);
	}

}

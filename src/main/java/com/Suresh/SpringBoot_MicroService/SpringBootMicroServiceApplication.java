package com.Suresh.SpringBoot_MicroService;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringBootMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMicroServiceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

}

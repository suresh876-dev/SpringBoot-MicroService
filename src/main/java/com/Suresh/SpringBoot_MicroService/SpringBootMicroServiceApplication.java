package com.Suresh.SpringBoot_MicroService;

import org.apache.kafka.clients.admin.NewTopic;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@CircuitBreaker
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

	@Bean
	public NewTopic createFirstTopic() {
		return TopicBuilder.name("First-topic").build();
	}

	@Bean
	public NewTopic createSecondTopic() {
		return TopicBuilder.name("Second-topic").build();
	}


}

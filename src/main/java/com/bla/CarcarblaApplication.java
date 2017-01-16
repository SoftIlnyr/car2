package com.bla;

import com.bla.repositories.UsersRepository;
import com.bla.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class CarcarblaApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CarcarblaApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CarcarblaApplication.class, args);
	}
//	@Bean
//	public CommandLineRunner demo(UsersRepository repository) {
//		return (args) -> {
//			// save a couple of customers
//			repository.save(new User("SoftIlnyr", "soft160896", "Ильнур", "Насибуллин", "softilnyr16@gmail.com", "admin", ""));
//		};
//	}
}

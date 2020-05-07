package com.jaga.usermanagement;

import com.jaga.usermanagement.entity.User;
import com.jaga.usermanagement.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@Slf4j
public class UserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}


	// Below commented code is used for testing this application
	/*@Bean
	public CommandLineRunner test(UserRepository userRepository) {
		return args -> {

			log.info("################################Start##############################################");

			userRepository.save(new User("jaga","jaga@mail.com","mypasswd", new Date()));
			userRepository.save(new User("jaga","jagaduplicate@mail.com","mypasswd", new Date()));
			userRepository.save(new User("Raja","raja@mail.com","mypasswd", new Date()));

			userRepository.findAll().forEach(user -> {
				log.info("User : {} ", user);
			});

			log.info("Found User By Name : {} ",userRepository.findByName("Raja"));
			log.info("Found User By Email : {} ",userRepository.findByEmail("jaga@mail.com"));

			log.info("###################################End###########################################");

		};
	}*/

}

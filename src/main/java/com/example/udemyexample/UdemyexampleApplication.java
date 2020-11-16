package com.example.udemyexample;

import com.example.udemyexample.repository.RepositoryUser;
import com.example.udemyexample.database.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UdemyexampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdemyexampleApplication.class, args);
	}

}

class CommandRunner implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(CommandRunner.class);

	RepositoryUser repositoryUser;

	@Override
	public void run(String... args) throws Exception {
		for(User user: repositoryUser.findAll()){
			LOG.info(user.toString());
		}

	}
}

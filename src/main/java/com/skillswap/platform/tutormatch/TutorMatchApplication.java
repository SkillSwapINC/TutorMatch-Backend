package com.skillswap.platform.tutormatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * TutorMatchApplication
 *
 * @summary
 * The main class of the TutorMatch application.
 * It is responsible for starting the Spring Boot application.
 * It also enables JPA auditing.
 *
 * @since 1.0
 */
@EnableJpaAuditing
@SpringBootApplication
public class TutorMatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutorMatchApplication.class, args);
	}

}

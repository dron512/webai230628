package com.jpa.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OrgApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrgApplication.class, args);
	}

}

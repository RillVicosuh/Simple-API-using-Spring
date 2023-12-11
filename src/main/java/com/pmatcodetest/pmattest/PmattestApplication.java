package com.pmatcodetest.pmattest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages={"com.pmatcodetest.pmattest.repository"})
@EntityScan(basePackages = "com.pmatcodetest.pmattest.model") 
//@EnableOpenApi
//@OpenAPIDefinition
public class PmattestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmattestApplication.class, args);
	}

}

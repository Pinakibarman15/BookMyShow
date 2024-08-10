package com.restoreBookshow.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan("com.restoreBookshow.demo")
@EnableJpaRepositories(basePackages="com.restoreBookshow.demo")
@EnableTransactionManagement
@EntityScan(basePackages="com.restoreBookshow.demo.models")
@EnableJpaAuditing
public class BookShowApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookShowApplication.class, args);
	}

}

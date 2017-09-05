package com.generateproject.jy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.generateproject.jy.controller")
@SpringBootApplication
public class JyApplication {

	public static void main(String[] args) {
		SpringApplication.run(JyApplication.class, args);
	}
}

package com.blogapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
// can remove or leave since SpringBootApplication scans all packages from top level
@ComponentScan(basePackages = {
		"com.blogapp.controllers",
		"com.blogapp.domains",
		"com.blogapp.repositories",
		"com.blogapp.services"
})
public class SpringApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringApp.class, args);
	}

}


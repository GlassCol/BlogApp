package com.blogapp;

import com.blogapp.utils.DataInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.blogapp")
public class SpringApp {

	public static void main(String[] args) {
		ApplicationContext appContext = SpringApplication.run(SpringApp.class, args);
		DataInitializer dataInitializer = appContext.getBean(DataInitializer.class);
		dataInitializer.seedBlogCategories();
		dataInitializer.seedPostsAndCommentsWithUsers();
	}

}


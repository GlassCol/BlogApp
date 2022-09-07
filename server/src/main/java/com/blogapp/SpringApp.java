package com.blogapp;

import com.blogapp.utils.DataInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.blogapp")
//@ImportResource({"hibernate.cfg.xml"})
public class SpringApp {

	public static void main(String[] args) {
<<<<<<< HEAD

		// THIS CODE DOES NOTHING AND DOES NOT WORK

		// read docs on @SpringBootApplication annotation regarding configuration
		// Spring boot autoconfigures based on the maven packages that are in the pom.xml file

		// Hibernate is also the default implementation for Soring-Boot-Starter-Jpa
		// so the hibernate.cfg file is not required
		// the Hibernate configuration should be in the application.properties file

		ConfigurableApplicationContext appContext = SpringApplication.run(SpringApp.class, args);

		DataInitializer dataInitializer = appContext.getBean(DataInitializer.class);
		dataInitializer.seedDb();
=======
		ApplicationContext appContext = SpringApplication.run(SpringApp.class, args);
		DataInitializer dataInitializer = appContext.getBean(DataInitializer.class);
		dataInitializer.seedBlogCategories();


>>>>>>> 21a2c00a0318e5e79df0178a813e9ad2509d61e6

	}


}


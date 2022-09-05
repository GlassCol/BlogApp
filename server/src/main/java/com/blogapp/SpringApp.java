package com.blogapp;

import com.blogapp.user.domain.User;
import com.blogapp.utils.DataInitializer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan("com.blogapp")
//@ImportResource({"hibernate.cfg.xml"})
public class SpringApp {

	public static void main(String[] args) {

		// @author WRITING AND PUSHING CODE THAT DOES NOT WORK

		// read docs on @SpringBootApplication annotation
		// Spring autoconfigures based on the maven packages
		// that are in the pom.xml file

		// Also, hibernate is the default implementation for Soring-Boot-Starter-Jpa
		// so all you need to do is add what you put in the hibernate.cfg file into
		// the application.properties file and annotate your domain classes as an
		// @Entity

		// The below does nothing and does not even work:
		// You should not be adding code that does work.
		// Another thing, even if the below code did work, you have not even registered
		// the configuration class into the spring app context

		// CODE THAT DOES NOT WORK
//		Configuration cfg = new Configuration();
//		cfg.configure("hibernate.cfg.xml");
//		SessionFactory factory = cfg.buildSessionFactory();
//
//		Session session = factory.openSession();
//		Transaction t = session.beginTransaction();
//
//		User u1 = new User();
//		u1.setFirstName("bill");
//		u1.setLastName("burr");
//		u1.setUsername("notBill");
//		u1.setAuthToken("burrbill");
//
//		session.save(u1);
		// END = CODE THAT DOES NOT WORK

		ConfigurableApplicationContext appContext = SpringApplication.run(SpringApp.class, args);

		DataInitializer dataInitializer = appContext.getBean(DataInitializer.class);
		dataInitializer.seedDb();


//		t.commit();
//		System.out.println("------Saved");


	}


}


package com.blogapp;

import com.blogapp.domains.User;
import com.blogapp.utils.DataInitializer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.blogapp")
public class SpringApp {

	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		User u1 = new User();
		u1.setFirstName("bill");
		u1.setLastName("burr");
		u1.setUsername("notBill");
		u1.setAuthToken("burrbill");

		session.save(u1);

		ApplicationContext appContext = SpringApplication.run(SpringApp.class, args);
		DataInitializer dataInitializer = appContext.getBean(DataInitializer.class);
		dataInitializer.seedBlogCategories();

		t.commit();
		System.out.println("------Saved");

	}

}


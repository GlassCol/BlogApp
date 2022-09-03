package com.blogapp;

import com.blogapp.utils.DataInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan("com.blogapp")
@ImportResource({"hibernate.cfg.xml"})
public class SpringApp {

	public static void main(String[] args) {
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

		ConfigurableApplicationContext appContext = SpringApplication.run(SpringApp.class, args);

		DataInitializer dataInitializer = appContext.getBean(DataInitializer.class);
		dataInitializer.seedDb();
//		dataInitializer.seedBlogCategories();
//		dataInitializer.seedPostsAndCommentsWithUsers();
//=======
//
//		t.commit();
//		System.out.println("------Saved");
//
//>>>>>>> a991e9b8460bf4e40ca11a95461229ea404478d0
	}


}


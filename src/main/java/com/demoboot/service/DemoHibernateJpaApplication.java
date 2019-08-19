package com.demoboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demoboot.service.hibernate.Demo1;

@SpringBootApplication
public class DemoHibernateJpaApplication implements CommandLineRunner {

	@Autowired
	private Demo1 demo1;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoHibernateJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		demo1.executeNormalQuery();
		
	}

}

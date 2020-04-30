package com.learn2earn.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.learn2earn.todo.services.EmailService;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner {

	@Autowired
	private EmailService emailService;

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello Dude..");

		//emailService.sendMail("vharishtavva@gmail.com", "Good", "Good email body.");
	}

}

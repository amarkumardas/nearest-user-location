package com.example.user.location.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class UserLocationProblemApplication implements CommandLineRunner {
	@Autowired
	private PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(UserLocationProblemApplication.class, args);
	}

	/**
	 * Since there are only two users, 'admin' and 'reader', their usernames and passwords are hardcoded.
	 * This is done to display them on the console to inform the developers while login. */
	@Override
	public void run(String... args) throws Exception {
		System.out.println("------------------------------");
		System.out.println("Admin userName: admin");
		System.out.println("Admin password: 123");
		System.out.println("------------------------------");
		System.out.println("Reader userName: reader");
	  	System.out.println("Reader password: 123");
		System.out.println("------------------------------");
	}
}

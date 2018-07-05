package com.lam.StudentManagement3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StudentManagement3Application {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagement3Application.class, args);
	}
}

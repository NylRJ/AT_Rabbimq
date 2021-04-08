package com.i9developement.classroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ClassroomApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassroomApplication.class, args);
	}

}

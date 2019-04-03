package com.broada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RayvMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(RayvMongodbApplication.class, args);
	}
}

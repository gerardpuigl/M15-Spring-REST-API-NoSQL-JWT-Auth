package com.itacademy.dicegame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class DicegameApplication {

	public static void main(String[] args) {
		SpringApplication.run(DicegameApplication.class, args);
	}

}

package com.example.myvue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;

@SpringBootApplication
@MapperScan("com.example.myvue.dao")
public class MyvueApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyvueApplication.class, args);
	}

}

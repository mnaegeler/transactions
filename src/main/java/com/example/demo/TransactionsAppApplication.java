package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableConfigurationProperties
@SpringBootApplication
@EntityScan(basePackages = "com.example.demo.entities")
public class TransactionsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionsAppApplication.class, args);
	}

}

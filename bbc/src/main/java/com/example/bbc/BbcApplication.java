package com.example.bbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value= {"com.example.bbc.mapper"})
public class BbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(BbcApplication.class, args);
	}

}

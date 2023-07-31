package com.pmh.my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyApplication {

	public static String KEY = "aaa.bbb.ccc";

	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
	}

}

package com.kakao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BlogsearchserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogsearchserviceApplication.class, args);
	}

}

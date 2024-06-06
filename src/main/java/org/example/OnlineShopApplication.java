package org.example;

import org.example.util.MD5Util;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class OnlineShopApplication {
	public static void main(String[] args) {
		SpringApplication.run(OnlineShopApplication.class, args);
//		System.out.printf(MD5Util.getMD5("1234"));
	}

}

package com.quadecom.QuadrantEcom;

import jakarta.servlet.http.HttpServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class QuadrantEcomApplication extends HttpServlet {

	public static void main(String[] args) {
		SpringApplication.run(QuadrantEcomApplication.class, args);
	}

}

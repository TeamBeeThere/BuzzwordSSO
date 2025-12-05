package com.emerald.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.emerald.config.SpringConfig;

@SpringBootApplication
@ComponentScan(basePackages = "com.emerald")
@Import(SpringConfig.class)
public class SsoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsoApplication.class, args);
	}

}

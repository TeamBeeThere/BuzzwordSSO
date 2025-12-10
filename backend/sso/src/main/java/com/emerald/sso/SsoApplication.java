package com.emerald.sso;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.emerald.config.SpringConfig;

@SpringBootApplication
@ComponentScan(basePackages = {
	"com.emerald.service",
	"com.emerald.controller"
})
@EntityScan(basePackages = "com.emerald.model")
@EnableJpaRepositories(basePackages = "com.emerald.repository")
@Import(SpringConfig.class)
public class SsoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsoApplication.class, args);
	}

}

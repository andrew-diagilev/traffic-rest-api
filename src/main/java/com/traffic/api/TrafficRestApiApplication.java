package com.traffic.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.Resources;

@SpringBootApplication
@AutoConfigurationPackage
public class TrafficRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrafficRestApiApplication.class, args);
	}

}

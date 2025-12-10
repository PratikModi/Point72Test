package com.point72.invertservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Point72TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(Point72TestApplication.class, args);
    }

}

package com.wanda.register2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ComponentScan(basePackages = {"com.wanda"})
@SpringBootApplication
public class Register2Application {

    public static void main(String[] args) {
        SpringApplication.run(Register2Application.class, args);
    }

}

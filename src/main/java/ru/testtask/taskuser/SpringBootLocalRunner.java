package ru.testtask.taskuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("dev")
public class SpringBootLocalRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootLocalRunner.class, args);
    }
}

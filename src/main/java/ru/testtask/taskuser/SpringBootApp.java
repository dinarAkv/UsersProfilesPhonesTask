package ru.testtask.taskuser;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpringBootApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringBootLocalRunner.class)
                .profiles("prod")
                .web(WebApplicationType.SERVLET)
                .build()
                .run(args);
    }
}

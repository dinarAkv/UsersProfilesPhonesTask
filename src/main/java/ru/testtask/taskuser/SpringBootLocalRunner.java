package ru.testtask.taskuser;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootLocalRunner extends SpringBootServletInitializer {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringBootLocalRunner.class)
                .profiles("dev")
                .web(WebApplicationType.SERVLET)
                .build()
                .run(args);
    }
}

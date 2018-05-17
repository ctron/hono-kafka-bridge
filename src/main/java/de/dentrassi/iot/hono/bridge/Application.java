package de.dentrassi.iot.hono.bridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:main.xml")
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

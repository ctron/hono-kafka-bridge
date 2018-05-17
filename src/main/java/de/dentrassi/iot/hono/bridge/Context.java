package de.dentrassi.iot.hono.bridge;

import org.apache.camel.component.amqp.AMQPConnectionDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Context {

    private @Value("${amqp.uri}") String uri;
    private @Value("${amqp.password}") String password;
    private @Value("${amqp.username}") String username;

    @Bean
    public AMQPConnectionDetails amqpConnection() {
        return new AMQPConnectionDetails(this.uri, this.username, this.password);
    }
}

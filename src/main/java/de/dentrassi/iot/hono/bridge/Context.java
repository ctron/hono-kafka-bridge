/*******************************************************************************
 * Copyright (c) 2018 Red Hat Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jens Reimann - initial API and implementation
 *******************************************************************************/

package de.dentrassi.iot.hono.bridge;

import org.apache.camel.component.amqp.AMQPConnectionDetails;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Context {

    private @Value("${amqp.uri}") String amqpUri;
    private @Value("${amqp.username}") String amqpUsername;
    private @Value("${amqp.password}") String amqpPassword;

    private @Value("${influx.uri}") String influxUrl;
    private @Value("${influx.username}") String influxUsername;
    private @Value("${influx.password}") String influxPassword;

    @Bean
    public AMQPConnectionDetails amqpConnection() {
        return new AMQPConnectionDetails(this.amqpUri, this.amqpUsername, this.amqpPassword);
    }

    @Bean(name = "influxDbConnectionBean")
    public InfluxDB influxDbConnection() {
        return InfluxDBFactory.connect(this.influxUrl, this.influxUsername, this.influxPassword);
    }
}

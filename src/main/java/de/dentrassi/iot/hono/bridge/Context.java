/*******************************************************************************
 * Copyright (c) 2018, 2020 Red Hat Inc and others.
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
public class Context {

    private @Value("${amqp.uri}") String amqpUri;
    private @Value("${amqp.username}") String amqpUsername;
    private @Value("${amqp.password}") String amqpPassword;

    @Bean
    public AMQPConnectionDetails amqpConnection() {
        if (StringUtils.isEmpty(this.amqpUsername)) {
            return new AMQPConnectionDetails(this.amqpUri);
        }

        return new AMQPConnectionDetails(this.amqpUri, this.amqpUsername, this.amqpPassword);
    }
}

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

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.camel.Message;
import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("metricsProcessor")
public class ProcessMetrics {

    private static final Logger logger = LoggerFactory.getLogger(ProcessMetrics.class);

    public Point transform(final Message msg) {

        final Point.Builder p = Point.measurement("P").time(System.currentTimeMillis(), TimeUnit.MILLISECONDS);

        for (final Map.Entry<String, ?> entry : msg.getHeaders().entrySet()) {
            if (!entry.getKey().startsWith("JMS") && entry.getValue() instanceof String) {

                switch (entry.getKey()) {
                case "breadcrumbId":
                    break;
                default:
                    p.tag(entry.getKey(), (String) entry.getValue());
                    break;
                }

            }
        }

        final Map<?, ?> values = msg.getBody(Map.class);
        if (values == null) {
            return null;
        }

        for (final Map.Entry<?, ?> entry : values.entrySet()) {
            if (!(entry.getKey() instanceof String)) {
                continue;
            }

            final String key = (String) entry.getKey();
            final Object value = entry.getValue();

            if (value == null) {
                continue;
            }

            if (value instanceof Number) {
                p.addField(key, (Number) value);
            } else if (value instanceof String) {
                try {
                    p.addField(key, Double.parseDouble((String) value));
                } catch (final Exception e) {
                    logger.debug("Failed to parse metric", e);
                }
            }
        }

        return p.build();
    }

}

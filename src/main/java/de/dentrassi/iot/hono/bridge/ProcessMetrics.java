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

import org.apache.qpid.proton.amqp.Symbol;
import org.apache.qpid.proton.message.Message;
import org.influxdb.dto.Point;
import org.springframework.stereotype.Component;

@Component("metricsProcessor")
public class ProcessMetrics {

    public Point transform(final Message msg) {

        final Point.Builder p = Point.measurement("P").time(System.currentTimeMillis(), TimeUnit.MILLISECONDS);

        for (final Map.Entry<Symbol, ?> entry : msg.getMessageAnnotations().getValue().entrySet()) {
            if (entry.getValue() instanceof String) {
                p.tag(entry.getKey().toString(), "" + entry.getValue());
                System.out.format("%s = %s%n", entry.getKey(), entry.getValue());
            }
        }

        /*
        final Map<?, ?> values = Json.decodeValue(string, Map.class);
        
        final Point.Builder p = Point.measurement("P").time(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        
        for (final Map.Entry<Symbol, ?> entry : msg.getMessageAnnotations().getValue().entrySet()) {
            if (entry.getValue() instanceof String) {
                p.tag(entry.getKey().toString(), "" + entry.getValue());
            }
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
        
        this.db.write(p.build());*/

        return null;
    }

}

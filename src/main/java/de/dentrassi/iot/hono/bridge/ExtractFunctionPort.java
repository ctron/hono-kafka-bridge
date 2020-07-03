/*******************************************************************************
 * Copyright (c) 2020 Red Hat Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jens Reimann - initial API and implementation
 *******************************************************************************/

package de.dentrassi.iot.hono.bridge;

import static java.util.Optional.ofNullable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExtractFunctionPort {

    private final static TypeReference<HashMap<String, Object>> TYPE_REF = new TypeReference<HashMap<String, Object>>() {};

    private final ObjectMapper mapper = new ObjectMapper();

    public Integer extractFunctionPort(final Exchange exchange) {
        return ofNullable(exchange
                .getIn()
                .getHeader("additional_data", String.class))
                        .map(this::unmarshal)
                        .map(json -> json.get("port"))
                        .map(Object::toString)
                        .map(Integer::parseInt)
                        .orElse(null);
    }

    private Map<String, Object> unmarshal(final String s) {
        try {
            return this.mapper.readValue(s, TYPE_REF);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

# Hono example bridge

This is a small project using Apache Camel to forward Eclipse Hono telemetry data to an
Apache Kafka cluster.

You can drop this into your OpenShift cluster by executing:

    oc new-app fabric8/s2i-java~https://github.com/ctron/hono-example-bridge

**Note:** This example assumes that Hono is deployed using [EnMasse](https://enmasse.io) 0.20.0
and that EnMasse is installed in a project called `enmasse`.
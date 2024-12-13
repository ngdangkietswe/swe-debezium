# SWE-DEBEZIUM

This repository contains the code for the Debezium project. Debezium is a distributed platform that turns your existing
databases into event streams, so applications can see and respond immediately to each row-level change in the databases.
Debezium is built on top of Apache Kafka and provides Kafka Connect compatible connectors that monitor specific database
management systems. Debezium records the history of data changes in Kafka logs, so your application can be stopped and
restarted at any time and can easily consume all the events it missed while it was not running, ensuring that all
events are processed correctly and completely.

## Tech stack

- [x] [Java](https://www.java.com/en/)
- [x] [Apache Kafka](https://kafka.apache.org/)
- [x] [Debezium](https://debezium.io/)
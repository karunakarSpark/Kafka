name := "Kafka"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies += "org.apache.kafka" % "kafka-clients" % "0.10.2.1"

libraryDependencies += "org.apache.kafka" %% "kafka" % "0.10.2.1"

libraryDependencies += "org.apache.kafka" % "kafka-streams" % "0.10.2.1"

libraryDependencies += "log4j" % "log4j" % "1.2.17"

libraryDependencies += "mysql" % "mysql-connector-java" % "6.0.4"

libraryDependencies += "kafka-avro-confluent" % "kafka-avro-confluent" % "0.1.0"

resolvers += "kafkaschemaRegistry" at "http://clojars.org/repo/"
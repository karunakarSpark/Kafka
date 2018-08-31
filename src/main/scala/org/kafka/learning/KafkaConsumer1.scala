package org.kafka.learning

import java.util.{Collections, Properties}

import scala.collection.JavaConversions._
import org.apache.kafka.clients.consumer.KafkaConsumer

object KafkaConsumer1 {
  def main(args: Array[String]): Unit = {

    val props = new Properties()
    props.put("bootstrap.servers", "172.16.38.131:9091,172.16.38.131:9092")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("group.id", "testgroup")
    //props.put("heartbeat.interval.ms", "4000") //milliseconds
    //props.put("client.id","ConsumerApp")

    //creation of a consumer object
    val consumer = new KafkaConsumer[String, String](props)

    //Specifying the topic name
    val topic = "testcalllog"

    //Making the consumer to subscribe to the topic
    consumer.subscribe(Collections.singletonList(topic))

    while (true) {
      val records = consumer.poll(100)
      for (record <- records.iterator()) {
        println("Received Message " + record)

      }
    }
    // consumer.commitSync()
  }

}

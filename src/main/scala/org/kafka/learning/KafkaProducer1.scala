package org.kafka.learning

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object KafkaProducer1 {
  def main(args: Array[String]): Unit = {

    val props = new Properties()

    props.put("bootstrap.servers", "172.16.38.131:9091")
    props.put("acks", "all") //gets acknowledment after all replications are made successfully
    //props.put("client.id", "ProducerApp")
   // props.put("retries", "4") //no. of retries in case of messages publishing
    //props.put("batch.size", "32768") //bytes
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer") //offset of the message
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer") //actual data

    //The topic to which the producer has to publish the data to.
    val topic = "testtopic2"

    //producer publishes data into a topic as messages
    //a message is made up of 2 parts (key,value)

    val msg: String = "Welcome to kafka"

    //creation of a Producer object
    val producer = new KafkaProducer[String, String](props)



    for( i <- 1 to 10)
    {
      val data = new ProducerRecord[String, String](topic, msg)
      producer.send(data)
    }
    producer.close()
    println("--------Successfully published messages to the topic:" + topic + "--------------------")
  }
}

package org.kafka.learning

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object KafkaFileProducer {

  def main(args: Array[String]): Unit = {
    val props  = new Properties()

    props.put("bootstrap.servers","172.16.38.131:9091,172.16.38.131:9092")

    props.put("acks", "all") //gets acknowledment after all replications are made successfully
   // props.put("client.id", "ProducerApp")
    //props.put("retries", "4") //no. of retries in case of messages publishing
    //props.put("batch.size", "32768") //bytes
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    //the topics to be created in Kafka
    val topic="testcalllog"

    //creation of a producer object
    val producer = new KafkaProducer[String,String](props)

    //file from which producer has to pull the data from
    val file = scala.io.Source.fromFile("C:\\Users\\KARUNAKAR\\Desktop\\calllogdata")

    for(line <- file.getLines()){
      if(line.contains("SUCCESS")==true) {
        val msg = new ProducerRecord[String, String](topic, 0,null,line)
        producer.send(msg)
      }
      else if(line.contains("FAILED")==true) {
        val msg = new ProducerRecord[String, String](topic, 1,null,line)
        producer.send(msg)
      }
      else
      {
        val msg = new ProducerRecord[String,String](topic, 2,null,line)
        producer.send(msg)
      }
    }
    producer.close()
  }
  println("---------successfully published messges to the topic(s)-------------")
}

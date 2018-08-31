package org.kafka.learning

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.errors.RecordTooLargeException
import org.apache.log4j.Logger

object ProducerSynchronousSend {
  val logger = Logger.getLogger(ProducerSynchronousSend.getClass)
  def main(args: Array[String]): Unit = {

    val props = new Properties()

    props.put("bootstrap.servers", "172.16.38.131:9091,172.16.38.131:9092")
    props.put("compression.type", "gzip")
    props.put("acks", "all")
    props.put("buffer.memory", "5254150")
    props.put("batch.size", "1048576")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("retries", "6")

    val producer = new KafkaProducer[String, String](props)

    val file = scala.io.Source.fromFile("C:\\Users\\KARUNAKAR\\Desktop\\calllogdata")

    for (line <- file.getLines()) {
      val record = new ProducerRecord[String, String]("topiccall3", 0, "calls", line)
      try {
        producer.send(record).get()
      }
      catch {
        case e: Exception =>
          //e.printStackTrace()
        logger.info("error",e)
          }
    }
  }
}

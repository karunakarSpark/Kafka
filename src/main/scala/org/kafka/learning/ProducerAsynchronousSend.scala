package org.kafka.learning

import java.util.Properties

import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerRecord, RecordMetadata}
import org.apache.log4j.Logger

object ProducerAsynchronousSend {
  def main(args: Array[String]): Unit = {

    val props = new Properties()

    props.put("bootstrap.servers", "172.16.38.131:9091,172.16.38.131:9092")
    props.put("compression.type", "gzip")
    props.put("acks", "all")
    props.put("buffer.memory", "1000000")
    props.put("batch.size", "100")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("retries", "6")

    val producer = new KafkaProducer[String, String](props)

    val file = scala.io.Source.fromFile("C:\\Users\\KARUNAKAR\\Desktop\\sampledata.txt")

    for (line <- file.getLines()) {
      val record = new ProducerRecord[String, String]("t2", 0, "calls", line)
        producer.send(record, new DemoProducerCallback())
          }
  }
}
    private class DemoProducerCallback extends Callback {
      @Override
      val logger = Logger.getLogger(ProducerAsynchronousSend.getClass)

      def onCompletion(recordmetadata: RecordMetadata, e: Exception): Unit = {
        if (e != null) {
        //  e.printStackTrace()
        logger.debug("error",e)
        }
      }
    }

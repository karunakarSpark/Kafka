package org.kafka.learning

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.log4j.Logger

import scala.io.Source

object ProducerSync {

  val logger = Logger.getLogger("ProducerSync")
  def main(args: Array[String]): Unit = {

    val props = new Properties

    props.put("bootstrap.servers","172.16.38.131:9091,172.16.38.131:9092")
    props.put("key.serializer","org.apache.kafka.common.serialization.IntegerSerializer")
    props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer")

    props.put("buffer.memory","100000")
    props.put("batch.size","1000")
    props.put("retries","5")
    props.put("acks","all")

    val producer = new KafkaProducer[Int,String](props)

    val file = Source.fromFile("C:\\Users\\KARUNAKAR\\Desktop\\sampledata.txt")

    for(line <- file.getLines()){
      val record = new ProducerRecord("t1",0,1001,line)
     try {
       val recordmetadata = producer.send(record).get()
       println(recordmetadata.topic+"---"+recordmetadata.offset())
     }
      catch {
        case e:Exception => logger.debug("this is the debgging info",e)
      }
     }
  }
}

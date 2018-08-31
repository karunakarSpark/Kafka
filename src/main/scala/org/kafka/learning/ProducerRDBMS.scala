package org.kafka.learning

import java.sql.{Connection, DriverManager}
import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import scala.io.Source

object ProducerRDBMS {
  def main(args: Array[String]): Unit = {

    val props = new Properties

    props.put("bootstrap.servers","172.16.38.131:9091,172.16.38.131:9092")
    props.put("key.serializer","org.apache.kafka.common.serialization.IntegerSerializer")
    props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer")

    props.put("buffer.memory","1000000")
    props.put("batch.size","1000000")
    props.put("retries","5")
    props.put("acks","all")

    val producer = new KafkaProducer[Int,String](props)


    val url = "jdbc:mysql://172.16.38.131:3306"
    val driver = "com.mysql.jdbc.Driver"
    val username = "KARUNAKAR"
    val password = "karna"
    var connection: Connection = null
    try {
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
      val statement = connection.createStatement
      val rs = statement.executeQuery("SELECT * FROM sample.calllog limit 1000")
      while (rs.next) {
        val status = rs.getString("status")
        val callnum = rs.getString("call_num")

        val record = new ProducerRecord("t4",0,1001,status+","+callnum)
        producer.send(record).get()
      }
    } catch {
      case e: Exception => e.printStackTrace
        connection.close
    }


      }
}
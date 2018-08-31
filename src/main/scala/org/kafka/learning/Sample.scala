package org.kafka.learning

import org.apache.log4j.Logger

object Sample {
val logger = Logger.getLogger("Sample")

  def main(args: Array[String]): Unit = {
    val  p = 20
    val q = 0
    try {
      val z = p / q
      println(z)
    }
    catch{
      case e:Exception =>
        logger.debug("error")
    }
  }
}

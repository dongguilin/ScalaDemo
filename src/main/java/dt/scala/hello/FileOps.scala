package dt.scala.hello

import scala.io.Source

/**
 * Created by hadoop on 2015/9/27.
 */
object FileOps {

  def main(args: Array[String]) {
    //    		val file = Source.fromFile("E:/Test/scala/test1.txt")
    val file = Source.fromURL("http://spark.apache.org/")
    for (line <- file.getLines) {
      println(line)
    }
  }

}

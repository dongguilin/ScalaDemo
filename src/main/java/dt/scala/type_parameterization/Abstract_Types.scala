package dt.scala.type_parameterization


import scala.io.{BufferedSource, Source}

/**
 * Created by hadoop on 2015/11/8.
 * Scala中Abstract types
 */
trait Reader {
  type In <: java.io.Serializable
  type Contents

  def read(in: In): Contents
}

class FileReader extends Reader {
  type In = String
  type Contents = BufferedSource

  override def read(in: In) = {
    Source.fromFile(in)
  }
}

object Abstract_Types {
  def main(args: Array[String]) {
    val fileReader = new FileReader
    val content = fileReader.read("E:/Test/test1.txt")
    for (line <- content.getLines()) println(line)
  }
}

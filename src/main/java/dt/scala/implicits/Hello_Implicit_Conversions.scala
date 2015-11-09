package dt.scala.implicits

import java.io.File

import scala.io.Source

/**
 * Created by hadoop on 2015/11/9.
 * Scala中隐式转换初体验
 */
class RichFile(val file: File) {
  def read = Source.fromFile(file.getPath).mkString
}

object Context {
  implicit def file2RichFile(file: File) = new RichFile(file) //File->RichFile
}

object Hello_Implicit_Conversions {
  def main(args: Array[String]) {
    import Context.file2RichFile
    println(new File("E:/Test/test1.txt").read)
  }
}

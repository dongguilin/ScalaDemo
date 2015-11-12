package dt.scala.implicits

import java.io.File

import scala.io.Source

/**
 * Created by hadoop on 2015/11/12.
 * Scala中隐式转换最佳实践
 */
class RicherFile(val file: File) {
  def read = Source.fromFile(file).mkString
}

class File_Implicits(path: String) extends File(path)

object File_Implicits {
  implicit def file2RicherFile(file: File) = new RicherFile(file) //File -> RicherFile
}

object Implicits_Internal {
  def main(args: Array[String]) {
    println(new File_Implicits("E:/Test/test1.txt").read)
  }
}

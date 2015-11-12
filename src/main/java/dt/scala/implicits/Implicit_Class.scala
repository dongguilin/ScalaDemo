package dt.scala.implicits

import java.io.File

import scala.io.Source

/**
 * Created by hadoop on 2015/11/12.
 * Scala中的隐式类
 */
object Context_Helper {

  implicit class FileEnhancer(file: File) {
    def read = Source.fromFile(file).mkString
  }

  implicit class Op(x: Int) {
    def addSAP(second: Int) = x + second
  }

}

object Implicit_Class {

  def main(args: Array[String]) {
    import Context_Helper._
    println(new File("E:/Test/test1.txt").read)
    println(1.addSAP(2))
  }

}

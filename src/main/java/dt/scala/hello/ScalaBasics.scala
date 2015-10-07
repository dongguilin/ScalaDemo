package dt.scala.hello

import scala.io.Source

/**
 * Created by hadoop on 2015/9/23.
 * Scala函数定义、流程控制、异常处理入门
 */
object ScalaBasics {

  def looper(x: Long, y: Long): Long = {
    var a = x
    var b = y
    while (a != 0) {
      val temp = a
      a = b % a
      b = temp
    }
    b
  }

  //  var line = ""
  //  do {
  //    println("Please input some words blow......")
  //    line = readLine()
  //    println("Read: " + line)
  //  } while (line != "")

  def doWhile() {
    var line = ""
    do {
      line = readLine()
      println("Read: " + line)
    } while (line != "")
  }

  def main(args: Array[String]) {
    println("This is Spark!!!")
    for (arg <- args) println(arg)

    var file = "scala.txt"
    if (!args.isEmpty) file = args(0)
    val file2 = if (!args.isEmpty) args(0) else "scala.xml"
    println(file2)

    println(if (!args.isEmpty) args(0) else "Spark.xml")

    println(looper(100, 298)) //2

    for (i <- 1 to 10) {
      println("Number is :" + i)
    }

    val files = (new java.io.File(".")).listFiles()
    for (file <- files) {
      println(file.getName)
    }

    println("-------------------------------")
    val file3 = "E:/Test/scala/test1.txt"
    println(Source.fromFile(file3).mkString)
    println("-------------------------------")

    val n = 99
    try {
      val half = if (n % 2 == 0) n / 2
      else throw
        new RuntimeException("N must be event")
      // Use the file
    } catch {
      case e: Exception => println("The exception is :" + e.getMessage())
    } finally {
      //      close(file)
    }

  }

}

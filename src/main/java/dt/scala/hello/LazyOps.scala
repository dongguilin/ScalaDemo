package dt.scala.hello

import scala.io.Source

/**
 * Created by hadoop on 2015/9/26.
 * Lazy的使用
 */
object LazyOps {

  def main(args: Array[String]) {

    //    val file = Source.fromFile("d:/he.txt")//FileNotFoundException
    lazy val file = Source.fromFile("E:/Test/scala/test12.txt")
    println("hello")
  }

}

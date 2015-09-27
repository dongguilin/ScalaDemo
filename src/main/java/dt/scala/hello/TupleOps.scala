package dt.scala.hello

/**
 * Created by hadoop on 2015/9/26.
 * 元组
 */
object TupleOps {

  def main(args: Array[String]) {

    val tuple = ("hello", "world", 124)
    println(tuple._1) //hello
    println(tuple._2) //world
    println(tuple._3) //124
    println(tuple.toString()) //(hello,world,124)

  }

}

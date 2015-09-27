package dt.scala.hello

/**
 * Created by hadoop on 2015/9/26.
 */
object MapOps {

  def main(args: Array[String]) {

    val map = Map("hello" -> "world", 11 -> 22, "hehe" -> 111)

    for ((k, v) <- map) print(k + "," + v + "\n")

    for ((k, _) <- map) print(k + " ")

  }

}

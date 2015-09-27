package dt.scala.hello

/**
 * Created by hadoop on 2015/9/26.
 */
object ArrayOperations {

  def main(args: Array[String]): Unit = {

    val array = Array(1, 2, 3, 4, 5)

    for (i <- 0 until array.length) {
      print(array(i) + " ")
    }

    println

    for (i <- 0 to array.length - 1) {
      print(array(i) + " ")
    }

    println

    for (elem <- array) {
      print(elem + " ")
    }

  }

}

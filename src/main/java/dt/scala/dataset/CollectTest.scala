package dt.scala.dataset

/**
 * Created by hadoop on 2015/12/13.
 */
object CollectTest {

  def main(args: Array[String]) {

    val a = List(4, 8, 12, 5, 9, "hehe", "hello")
    val a2 = a.collect {
      case x: Int => x
    }
    println(a2)

  }


}

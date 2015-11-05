package dt.scala.type_parameterization

/**
 * Created by hadoop on 2015/11/5.
 * Infix Type
 */
object Infix_Types {

  def main(args: Array[String]) {

    object Log {
      def >>:(data: String): Log.type = {
        println(data)
        Log
      }
    }

    "Hadoop" >>: "Spark" >>: Log

    val list = List()
    val newList = "A" :: "B" :: list
    println(newList)

    class Infix_Type[A, B]
    val infix: Int Infix_Type String = null
    val infix1: Infix_Type[Int, String] = null

    case class Cons(first: String, second: String)
    val case_class = Cons("one", "two")
    case_class match {
      case "one" Cons "two" => println("Spark!!!")
    } //unapply

  }

}

package dt.scala.type_parameterization


/**
 * Created by hadoop on 2015/10/17.
 * Scala中泛型类、泛型函数、泛型在Spark中的应用
 */
class Triple[F, S, T](first: F, second: S, third: T)

object Hello_Type_Parameterization {

  def main(args: Array[String]) {

    val triple = Triple("Spark", 3, 3.1415)
    val bigData = Triple[String, String, Char]("Spark", "Hadoop", 'R')

    def getData[T](list: List[T]) = list(list.length / 2)
    println(getData(List("Spark", "Hadoop", 'R))) //Hadoop

    val f = getData[Int] _
    println(f(List(3, 4, 5, 6, 7))) //5

    val queue = scala.collection.immutable.Queue(1, 2, 3, 4, 5)
    val queue_appended = queue enqueue (6)
    println("queue : " + queue + "   " + "queue_appended : " + queue_appended) //queue : Queue(1, 2, 3, 4, 5)   queue_appended : Queue(1, 2, 3, 4, 5, 6)

  }

}

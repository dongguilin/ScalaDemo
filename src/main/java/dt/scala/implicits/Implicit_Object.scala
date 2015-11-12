package dt.scala.implicits

/**
 * Created by hadoop on 2015/11/12.
 * Scala中的隐式对象
 */
abstract class Template[T] {

  //  def compare(x: T, y: T): Int

}

abstract class SubTemplate[T] extends Template {
  def add(x: T, y: T): T

  def unit: T
}

object Implicit_Object {

  def main(args: Array[String]) {
    implicit object StringAdd extends SubTemplate[String] {
      override def add(x: String, y: String) = x concat y

      override def unit: String = ""

    }

    implicit object IntAdd extends SubTemplate[Int] {
      override def add(x: Int, y: Int) = x + y

      override def unit: Int = 0

    }

    def sum[T](x: List[T])(implicit m: SubTemplate[T]): T = {
      if (x.isEmpty) m.unit
      else m.add(x.head, sum(x.tail))
    }

    println(sum(List(1, 2, 3, 4, 5)))
    println(sum(List("Scala", "Hadoop", "Spark")))

  }

}

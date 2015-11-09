package dt.scala.implicits

/**
 * Created by hadoop on 2015/11/9.
 * Scala中隐式参数与隐式转换的联合使用
 */
object Implicit_Conversions_with_Implicit_Parameters {

  def main(args: Array[String]) {

    def bigger[T](a: T, b: T)(implicit ordered: T => Ordered[T]) = if (a > b) a else b

    println(bigger(3, 4))

    println(bigger("Spark", "Hadoop"))

    println(bigger(4, 4.2))

  }

}

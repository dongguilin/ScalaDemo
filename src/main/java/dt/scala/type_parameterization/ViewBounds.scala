package dt.scala.type_parameterization

/**
 * Created by hadoop on 2015/10/17.
 * Scala中视图界定View Bounds
 *
 */
object ViewBounds {

  def main(args: Array[String]) {
    val pair = new Pair_NotPerfect[String]("Spark", "Hadoop")
    println(pair.bigger)

    val pairInt = new Pair_NotPerfect[Int](3, 5) //Int会被隐式转换为RichInt，RichInt实现了Comparable
    println(pairInt.bigger)

    val pair_better_string = new Pair_Perfect[String]("Spark", "Hadoop") //String可以被隐式转换为RichString,而RichString是Ordered[String]的子类型
    println(pair_better_string.bigger)

    val pair_better_int = new Pair_Perfect[Int](3, 5)
    println(pair_better_int.bigger)
  }

}

//class Pair_NotPerfect[T <: Comparable[T]](val first: T, val second: T) {
//  def bigger = if (first.compareTo(second) > 0) first else second
//}

// <% 视图界定
class Pair_NotPerfect[T <% Comparable[T]](val first: T, val second: T) {
  def bigger = if (first.compareTo(second) > 0) first else second
}

class Pair_Perfect[T <% Ordered[T]](val first: T, val second: T) {
  def bigger = if (first > second) first else second
}

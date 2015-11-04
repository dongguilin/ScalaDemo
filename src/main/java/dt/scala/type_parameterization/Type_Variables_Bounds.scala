package dt.scala.type_parameterization

/**
 * Created by hadoop on 2015/10/17.
 * Scala中类型变量Bounds
 */
class Pair[T <: Comparable[T]](val first: T, val second: T) {
  def bigger = if (first.compareTo(second) > 0) first else second
}

class Pair_Lower_Bound[T](val first: T, val second: T) {
  def replaceFirst[R >: T](newFirst: R) = new Pair_Lower_Bound[R](newFirst, second)
}

object Type_Variables_Bounds {

  def main(args: Array[String]) {
    val pair = new Pair[String]("Spark", "Hadoop")
    println(pair.bigger)
  }

}

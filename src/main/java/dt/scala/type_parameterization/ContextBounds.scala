package dt.scala.type_parameterization

/**
 * Created by hadoop on 2015/10/17.
 * Scala中上下文界定ContextBounds
 * 形式为T:M，其中M是另一个泛型类，它要求必须存在一个类型为M[T]的“隐式值”
 */
object ContextBounds {

  def main(args: Array[String]) {
    val pairStr = new Pair_Ordering[String]("Hadoop", "Spark")
    println(pairStr.bigger)

    val pairInt = new Pair_Ordering[Int](3, 5)
    println(pairInt.bigger)
  }

}

class Pair_Ordering[T: Ordering](val first: T, val second: T) {
  def bigger(implicit ordered: Ordering[T]) = {//ordered为添加的隐式参数
    if (ordered.compare(first, second) > 0) first else second
  }
}


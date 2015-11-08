package dt.scala.type_parameterization

/**
 * Created by hadoop on 2015/11/6.
 * Scala中Variance变化点
 */
class P[+T](val first: T, val second: T) {
  def replaceFirst[R >: T](newFirst: R) = new P[R](newFirst, second)
}

object Variant_Positions {

}

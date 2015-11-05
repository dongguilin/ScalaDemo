package dt.scala.type_parameterization

/**
 * Created by hadoop on 2015/11/5.
 * Scala路径依赖
 */
class Outer {
  private val x = 10

  class Inner {
    private val y = x + 10
  }

}

object Path_Dependence {

  def main(args: Array[String]) {
    val outer = new Outer
    val inner = new outer.Inner
    val inner2: outer.Inner = new outer.Inner
    println(inner == inner2)

    val o1 = new Outer
    val o2 = new Outer
    val i: Outer#Inner = new o1.Inner
    //error
    //    val i2:o1.Inner = new o2.Inner
    val i2: o2.Inner = new o2.Inner
  }
}

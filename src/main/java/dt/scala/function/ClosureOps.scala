package dt.scala.function

/**
 * Created by hadoop on 2015/10/7.
 * Scala中的闭包
 * 闭包由代码和代码用到的任何非局部变量定义构成
 *
 */
object ClosureOps {

  def main(args: Array[String]) {
    val data = List(1, 2, 3, 4, 5, 6)
    var sum = 0
    data.foreach(sum += _)
    println(sum)

    def add(more: Int) = (x: Int) => x + more
    val a = add(1)
    val b = add(9999)
    println(a(10))
    println(b(10))
    println(add(2)(3))
  }

}

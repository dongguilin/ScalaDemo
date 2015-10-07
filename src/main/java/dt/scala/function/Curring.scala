package dt.scala.function

/**
 * Created by hadoop on 2015/10/7.
 * 柯里化
 * 将原来接受两个参数的函数变成新的接受一个参数的函数的过程
 */
object Curring {

  def main(args: Array[String]) {
    def multiple(x: Int, y: Int) = x * y
    def multipleOne(x: Int) = (y: Int) => x * y
    println(multipleOne(6)(7))

    def curring(x: Int)(y: Int) = x * y

    println(curring(10)(10))

    val a = Array("Hello", "Spark")
    val b = Array("hello", "spark")
    println(a.corresponds(b)(_.equalsIgnoreCase(_))) //corresponds方法可以比较两个序列号是否在某个比对条件下相同
  }

}

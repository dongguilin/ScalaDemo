package dt.scala.type_parameterization

/**
 * Created by hadoop on 2015/11/5.
 * Scala结构类型
 */
class Structural {
  def open() = println("A class instance opened")
}

object Structural__Type {

  def init(res: {def open(): Unit}): Unit = {
    res.open()
  }

  def main(args: Array[String]): Unit = {
    init(new {
      def open() = println("hello")
    })

    type X = {def open(): Unit}
    def init(res: X) = res.open()

    init(new {
      def open() = println("world")
    })

    object A {
      def open() {
        println("A object open")
      }
    }
    init(A)

    init(new Structural)
  }

}

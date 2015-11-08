package dt.scala.type_parameterization

/**
 * Created by hadoop on 2015/10/17.
 * Scala多重界定
 * T <: A with B    T是A或者B的子类（或）
 * T >: A with B    A或者B是T的子类（或）
 * T >: A <: B      A是T的下界，B是T的上界，且下界写在前，上界写在后（并）
 * T : A : B        上下文界定，T可以隐式转换为A的类型且同时可以隐式转换为B的类型（并）
 * T <% A <% B      视图界定，T可以隐式转换为A的类型且同时可以隐式转换为B的类型（并）
 */
class M_A[T]

class M_B[T]

object MutipleBounds {

  def main(args: Array[String]) {
    implicit val a = new M_A[Int]
    implicit val b = new M_B[Int]
    def foo[T: M_A : M_B](i: T) = println("OK")
    foo(11)

    //error
    //    foo("hello")
  }

}

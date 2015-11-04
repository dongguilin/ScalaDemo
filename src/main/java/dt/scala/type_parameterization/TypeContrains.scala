package dt.scala.type_parameterization

/**
 * Created by hadoop on 2015/10/18.
 * Scala类型约束
 */
object TypeContrains {

  def main(args: Array[String]) {

    def rocky[T](i: T)(implicit m: T <:< java.io.Serializable) = {
      println("Life is short, you need Spark!")
    }
    rocky("hello")


    //1.类型约束可以在泛型类中定义只有在特定条件下使用的方法
    //可以构造出Pair[File]，尽管File并不是带先后次序的，只有当调用smaller方法时，才会报错
    class Pair[T](val first: T, val second: T) {
      def smaller(implicit ev: T <:< Ordered[T]) =
        if (first < second) first else second
    }

    //2.类型约束可以改进类型推断

    //error
    //    def firstLast[A, C <: Iterable[A]](it: C) = (it.head, it.last)
    //    firstLast(List(1, 2, 3))

    def firstLast[A, C](it: C)(implicit ev: C <:< Iterable[A]) = (it.head, it.last)
    firstLast(List(1, 2, 3))

  }

}







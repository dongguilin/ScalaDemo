package dt.scala.function

/**
 * Created by hadoop on 2015/10/7.
 * Scala高阶函数
 */
object HigherOrderFunction {

  def main(args: Array[String]) {
    (1 to 9).map("*" * _).foreach(println _)
    (1 to 9).filter(_ % 2 == 0).foreach(println)
    println((1 to 9).reduceLeft(_ * _))
    "Spark is the most exciting thing happening in big data today".split(" ").
      sortWith(_.length < _.length).foreach(println)
    println("----------------")


    import java.lang.Math._
    val fun = ceil _
    val num = 3.14
    println(fun(num))
    Array(3.14, 1.42, 2.0).map(fun).foreach(println)
    println("---------------------")

    val triple = (x: Double) => 3 * x
    Array(3.14, 1.42, 2.0).map((x: Double) => 3 * x)
    Array(3.14, 1.42, 2.0).map { (x: Double) => 3 * x }

    def HighOrderFunction(f: (Double) => Double) = f(0.25)
    println(HighOrderFunction(ceil _))
    println(HighOrderFunction(sqrt _))

    def mulBy(factor: Double) = (x: Double) => factor * x
    val quintuple = mulBy(5)
    println(quintuple(20))

    println(HighOrderFunction((x: Double) => 3 * x))
    HighOrderFunction((x) => 3 * x)
    HighOrderFunction(x => 3 * x)

    println(HighOrderFunction(3 * _))

    val fun2 = 3 * (_: Double)
    val fun3: (Double) => Double = 3 * _
    println(fun2(3), fun3(3))
  }

}

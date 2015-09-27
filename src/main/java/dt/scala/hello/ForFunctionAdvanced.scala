package dt.scala.hello

/**
 * Created by hadoop on 2015/9/26.
 */
object ForFunctionAdvanced {

  def main(args: Array[String]) {
    for (i <- 1 to 2; j <- 1 to 2) print((100 * i + j) + "  ") //101  102  201  202
    println

    //守卫
    for (i <- 1 to 2; j <- 1 to 2 if i != j) print((100 * i + j) + "  ") //102  201
    println

    def addA(x: Int) = x + 100
    println("The result from a function is : " + addA(2))
    //匿名函数
    val add = (x: Int) => x + 200
    println("The result from a val is : " + add(2))
    val tuple = (first: String, second: String, three: Int) => ("hello" + first, "world" + second + three)
    println(tuple.apply("1", "2", 123)._1) //hello1
    val tuple2 = (a: Int, b: Int) => {
        val i1 = a + b
        val i2 = a - b
        (i1, i2, a, b)
      }
    println(tuple2.apply(2, 3)) //(5,-1,2,3)


    def fac(n: Int): Int = if (n <= 0) 1 else n * fac(n - 1)
    println("The result from a fac is : " + fac(10)) //3628800
    println(10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1) //3628800

    //使用默认参数
    def combine(content: String, left: String = "[", right: String = "]") = left + content + right
    println("The result from a combine is : " + combine("I love Spark", "<<"))

    //可变参数
    def connected(args: Int*) = {
      var result = 0
      for (arg <- args) result += arg
      result
    }
    println("The result from a connected is : " + connected(1, 2, 3, 4, 5, 6)) //21
  }

}

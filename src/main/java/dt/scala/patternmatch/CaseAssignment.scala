package dt.scala.patternmatch

/**
 * Created by hadoop on 2015/11/15.
 * 模式匹配下的赋值语句
 */
object CaseAssignment {

  def main(args: Array[String]) {

    val a@b = 1000
    println("a = " + a + ", b = " + b)

    val hello@"world" = "world"
    println(hello)

    val (c, d) = (1000, "hello")
    //    val (c,D) = (1000,"hello")

    val Array(a1, b1) = Array(1000, 2000)

    object Test {
      val 1 = 1
    }
    object Test2 {
      val 1 = 2
    }

  }

}

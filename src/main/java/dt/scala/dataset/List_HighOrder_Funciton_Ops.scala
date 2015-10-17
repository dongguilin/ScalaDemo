package dt.scala.dataset

/**
 * Created by hadoop on 2015/10/14.
 * List的map、flatMap、foreach操作
 * List的partition、find、takeWhile、dropWhile、span、forall、exsits操作
 */
object List_HighOrder_Funciton_Ops {

  def main(args: Array[String]) {
    println(List(1, 2, 3, 4, 5, 6) map (_ + 1)) //List(2, 3, 4, 5, 6, 7)

    val data = List("Scala", "Hadoop", "Spark")
    println(data map (_.length)) //List(5, 6, 5)
    println(data map (_.toList.reverse.mkString)) //List(alacS, poodaH, krapS)

    println(data map (_.toList)) //List(List(S, c, a, l, a), List(H, a, d, o, o, p), List(S, p, a, r, k))
    println(data flatMap (_.toList)) //List(S, c, a, l, a, H, a, d, o, o, p, S, p, a, r, k)
    println(List.range(1, 10) flatMap (i => List.range(1, i) map (j => (i, j)))) //List((2,1), (3,1), (3,2), (4,1), (4,2), (4,3), (5,1), (5,2), (5,3), (5,4), (6,1), (6,2), (6,3), (6,4), (6,5), (7,1), (7,2), (7,3), (7,4), (7,5), (7,6), (8,1), (8,2), (8,3), (8,4), (8,5), (8,6), (8,7), (9,1), (9,2), (9,3), (9,4), (9,5), (9,6), (9,7), (9,8))

    var sum = 0
    List.range(1, 6) map (sum += _)
    println("sum:" + sum) //sum:15

    println(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10) filter (_ % 2 == 0)) //List(2, 4, 6, 8, 10)
    println(data filter (_.length == 5)) //List(Scala, Spark)

    println(List(1, 2, 3, 4, 5) partition (_ % 2 == 0)) //(List(2, 4),List(1, 3, 5))
    println(List(1, 2, 3, 4, 5) find (_ % 2 == 0)) //Some(2)
    println(List(1, 2, 3, 4, 5) find (_ <= 0)) //None
    println(List(1, 2, 3, 4, 5) takeWhile (_ < 4)) //List(1, 2, 3)
    println(List(1, 2, 3, 4, 5) dropWhile (_ < 4)) //List(4, 5)
    println(List(1, 2, 3, 4, 5) span (_ < 4)) //(List(1, 2, 3),List(4, 5))

    def hastotallyZeroRow(m: List[List[Int]]) = m exists (row => row forall (_ == 0))
    val m = List(List(1, 0, 0), List(0, 0, 0), List(0, 0, 1))
    println(hastotallyZeroRow(m)) //true

  }

}

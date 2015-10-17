package dt.scala.dataset

/**
 * Created by hadoop on 2015/10/14.
 * List伴生对象操作方法
 */
object ListObjectOps {

  def main(args: Array[String]) {
    println(List.apply(1, 2, 3)) //List(1, 2, 3)
    println(List.make(3, 5)) //List(5, 5, 5)
    println(List.range(1, 5)) //List(1, 2, 3, 4)
    println(List.range(9, 1, -3)) //List(9, 6, 3)

    val zipped = "abcde".toList zip List(1, 2, 3, 4, 5)
    println(zipped) //List((a,1), (b,2), (c,3), (d,4), (e,5))
    println(zipped.unzip) //(List(a, b, c, d, e),List(1, 2, 3, 4, 5))

    println(List(List('a', 'b'), List('c'), List('d', 'e')).flatten) //List(a, b, c, d, e)
    println(List.concat(List(), List('b'), List('c'))) //List(b, c

    println(List.map2(List(10, 20), List(10, 10))(_ * _)) //List(100, 200)

  }


}

package dt.scala.dataset

/**
 * Created by hadoop on 2015/12/13.
 */
object ZipTest {

  def main(args: Array[String]) {

    //zip
    val prices = List(5.0, 20.0, 9.95)
    val quantities = List(10, 2, 1)
    println(prices zip quantities) //List((5.0,10), (20.0,2), (9.95,1))
    println((prices zip quantities) map { p => p._1 * p._2 }) //List(50.0, 40.0, 9.95)

    //zipAll 可指定较短列的缺省值
    println(List(5.0, 20.0, 9.95) zipAll(List(10, 2), 0.0, 1)) //List((5.0,10), (20.0,2), (9.95,1))
    println(List(5.0, 20.0, 9.95) zipAll(List(10, 2, 3, 4, 5), 0.0, 1)) //List((5.0,10), (20.0,2), (9.95,3), (0.0,4), (0.0,5))

    //zipWithIndex
    println("Scala" zipWithIndex) //Vector((S,0), (c,1), (a,2), (l,3), (a,4))
    println("Scala".zipWithIndex.max) //(l,3)


  }

}

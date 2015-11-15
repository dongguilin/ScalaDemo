package dt.scala.list

/**
 * Created by hadoop on 2015/11/15.
 * Scala中List的构造是类型约束逆变、协变、下界
 */
abstract class BigData

class Hadoop extends BigData

class Spark extends BigData

object List_Constructor_Internals {

  def main(args: Array[String]) {
    val hadoop = new Hadoop :: Nil
    val bigData = new Spark :: hadoop
    println(bigData) //List(dt.scala.list.Spark@6bdf28bb, dt.scala.list.Hadoop@e580929)

    println(1 :: List(2, 3) == List(2, 3).::(1)) //true
    println(List(2, 3).::(1)) //List(1, 2, 3)
    println(3 :: List(5, 6).::(7).::(8).::(9)) //List(3, 9, 8, 7, 5, 6)
    println(List(2, 3) :: List(5, 6).::(7).::(8)) //List(List(2, 3), 8, 7, 5, 6)
    println(2::3::List(5))//List(2, 3, 5)
  }

}

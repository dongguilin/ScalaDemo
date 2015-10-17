package dt.scala.dataset

/**
 * Created by hadoop on 2015/10/12.
 * List的基本操作与基于模式匹配的List排序算法
 */
object HelloList {

  def main(args: Array[String]) {

    val bigData = List("Hadoop", "Spark")
    val data = List(1, 2, 3)
    println(bigData, data, List("hello", 12, List(11, 22)))

    val bigData_Core = "Hadoop" :: ("Spark" :: Nil)
    val data_Int = 1 :: 2 :: 3 :: Nil

    println(data.isEmpty) //false
    println(data.head) //1
    println(data.tail.head) //2

    //变量声明中的模式
    val List(a, b) = bigData
    println("a : " + a + " === " + " b: " + b) //a : Hadoop ===  b: Spark
    val x :: y :: rest = data
    println("x : " + x + " === " + " y: " + y + " === " + rest) //x : 1 ===  y: 2 === List(3)

    val shuffledData = List(6, 3, 5, 6, 2, 9, 1)
    println(sortList(shuffledData))

    //排序
    def sortList(list: List[Int]): List[Int] = list match {
      case List() => List()
      case head :: tail => compute(head, sortList(tail))
    }

    def compute(data: Int, dataSet: List[Int]): List[Int] = dataSet match {
      case List() => List(data)
      case head :: tail => if (data <= head) data :: dataSet
      else head :: compute(data, tail)
    }

  }

}

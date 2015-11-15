package dt.scala.list

/**
 * Created by hadoop on 2015/11/15.
 * ListBuffer
 */
object ListBufferInternal {

  def main(args: Array[String]) {
    println(increment(List(1, 2, 3)))
    println(increment2(List(1, 2, 3)))
    println(increment3(List(1, 2, 3)))
  }

  def increment(list: List[Int]): List[Int] = list match {
    case List() => List()
    case head :: tail => head + 1 :: increment(tail)
  }

  def increment2(list: List[Int]): List[Int] = {
    var result = List[Int]()
    for (element <- list) result = result ::: List(element + 1)
    result
  }

  //推荐
  def increment3(list: List[Int]): List[Int] = {
    import scala.collection.mutable.ListBuffer
    var buffer = new ListBuffer[Int]
    for (ele <- list) buffer += ele + 1
    buffer.toList
  }

}


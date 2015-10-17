package dt.scala.dataset

/**
 * Created by hadoop on 2015/10/15.
 * List的foldLeft、foldRight、sort
 */
object List_Fold_Sort {

  def main(args: Array[String]) {
    println((1 to 100).foldLeft(0)(_ + _)) //5050
    println((0 /: (1 to 100))(_ + _)) //5050
    println((1 to 5).foldRight(100)(_ - _)) //-97
    println(((1 to 5) :\ 100)(_ - _)) //-97
    println(List(1, -3, 4, 2, 6) sortWith (_ < _)) //List(-3, 1, 2, 4, 6)
    println(List(1, -3, 4, 2, 6) sortWith (_ > _)) //List(6, 4, 2, 1, -3)
  }

}

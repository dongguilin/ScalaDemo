package dt.scala.dataset

/**
 * Created by hadoop on 2015/10/15.
 * 默认引入的不可变List
 */
object List_Interal {

  def main(args: Array[String]) {

    //默认引入scala.collection.immutable.List
    val list: List[Int] = List(1, 2, 3, 4, 5)
    val listAny: List[Any] = list
    println(list, listAny, list == listAny) //(List(1, 2, 3, 4, 5),List(1, 2, 3, 4, 5),true)
    println(list.isEmpty) //false
    println(list.head) //1
    println(list.tail) //List(2, 3, 4, 5)
    println(list.tail.head) //2
    println(list.length) //5
    println(list.drop(2)) //List(3, 4, 5)
    list.map(_ * 2)
    println(list) //List(1, 2, 3, 4, 5)

  }

}

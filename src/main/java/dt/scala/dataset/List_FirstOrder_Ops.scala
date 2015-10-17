package dt.scala.dataset

/**
 * Created by hadoop on 2015/10/12.
 * List的一阶函数操作
 */
object List_FirstOrder_Ops {
  def main(args: Array[String]) {
    println(List(1, 2, 3, 4) ::: List(4, 5, 6, 7, 8) ::: List(10, 11))
    println(List(1, 2, 3, 4) ::: (List(4, 5, 6, 7, 8) ::: List(10, 11)))
    println(List(1, 2, 3, 4).length)

    val bigData = List("Hadoop", "Spark", "Kaffka")
    println(bigData.last) //Kaffka
    println(bigData.init) //List(Hadoop, Spark)
    println(bigData.reverse) //List(Kaffka, Spark, Hadoop)
    println(bigData) //List(Hadoop, Spark, Kaffka)
    println(bigData take 2) //List(Hadoop, Spark)
    println(bigData drop 1) //List(Spark, Kaffka)
    println(bigData splitAt 2) //(List(Hadoop, Spark),List(Kaffka))
    println(List("hello", "world", 11, 22, 33, 44) splitAt 2) //(List(hello, world),List(11, 22, 33, 44))
    println(bigData apply 2) //Kaffka
    println(bigData(2)) //Kaffka

    val data = List('a', 'b', 'c', 'd', 'e', 'f')
    println(data.indices) //Range(0, 1, 2, 3, 4, 5)
    println(data.indices zip data) //Vector((0,a), (1,b), (2,c), (3,d), (4,e), (5,f))
    println(data.zipWithIndex) //List((a,0), (b,1), (c,2), (d,3), (e,4), (f,5))
    println(data.toString) //List(a, b, c, d, e, f)
    println(data.mkString("[", ",,", "]")) //[a,,b,,c,,d,,e,,f]
    println(data.mkString("******")) //a******b******c******d******e******f
    println(data mkString) //abcdef

    val buffer = new StringBuilder
    data addString(buffer, "(", ";;", ")")
    println(buffer) //(a;;b;;c;;d;;e;;f)
    println(data) //List(a, b, c, d, e, f)

    val array = data.toArray
    println(array.toList) //List(a, b, c, d, e, f)

    val new_Array = new Array[Char](10)
    data.copyToArray(new_Array, 3)
    new_Array.foreach(print) //   abcdef
    println

    val iterator = data.toIterator
    println(iterator.next) //a
    println(iterator.next) //b
  }
}

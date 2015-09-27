package dt.scala.hello

/**
 * Created by hadoop on 2015/9/26.
 */
object MapTuple {

  def main(args: Array[String]) {

    val map = Map("book" -> 10, "gun" -> 18, "ipad" -> 1000)
    for ((k, v) <- map) yield (k, v * 0.9)

    //可变的map
    val scores = scala.collection.mutable.Map("Scala" -> 7, "Hadoop" -> 8, "Spark" -> 10)
    val hadoopScore = scores.getOrElse("Hadoop", 0)
    scores += ("R" -> 9)
    scores -= "Hadoop"
    println(scores) //Map(R -> 9, Spark -> 10, Scala -> 7)
    println(scores + ("hello" -> "world")) //Map(R -> 9, Spark -> 10, Scala -> 7, hello -> world)

    val sortedScore = scala.collection.immutable.SortedMap("Scala1" -> 7, "Hadoop" -> 8, "Spark" -> 10)
    println(sortedScore) //Map(Hadoop -> 8, Scala1 -> 7, Spark -> 10)

    val tuple = (1, 2, 3.14, "Rocky", "Spark")
    val third = tuple._3

    //匿名函数
    val (first, second, thirda, fourth, fifth) = tuple
    //_占位符
    val (f, s, _, _, _) = tuple

    //分组
    println("Rocky Spark".partition(_.isUpper)) //(RS,ocky park)

    //压缩
    val symbols = Array("[", "-", "]")
    val counts = Array(2, 5, 2)
    val pairs = symbols.zip(counts) //([,2) (-,5) (],2)
    for (elem <- pairs) {
      print(elem + " ")
    }
    println

    for ((x, y) <- pairs) print(x * y) //[[-----]]


  }

}

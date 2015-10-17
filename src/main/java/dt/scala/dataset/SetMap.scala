package dt.scala.dataset

import scala.collection.immutable.TreeMap
import scala.collection.mutable

/**
 * Created by hadoop on 2015/10/12.
 * Set、Map、TreeSet、TreeMap操作
 */
object SetMap {

  def main(args: Array[String]) {
    val data = mutable.Set.empty[Int]
    data ++= List(1, 2, 3)
    data += 4;
    data --= List(2, 3);
    println(data) //Set(1, 4)
    data += 1;
    println(data) //Set(1, 4)
    data.clear
    println(data) //Set()

    val map = mutable.Map.empty[String, String]
    map("Java") = "Hadoop"
    map("Scala") = "Spark"
    println(map) //Map(Scala -> Spark, Java -> Hadoop)
    println(map("Scala")) //Spark

    val treeSet = mutable.TreeSet(9, 3, 1, 8, 0, 2, 7, 4, 6, 5)
    println(treeSet) //TreeSet(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    val treeSetForChar = mutable.TreeSet("Spark", "Scala", "Hadoop")
    println(treeSetForChar) //TreeSet(Hadoop, Scala, Spark)

    var treeMap = TreeMap("Scala" -> "Spark", "Java" -> "Hadoop")
    println(treeMap) //Map(Java -> Hadoop, Scala -> Spark)
    treeMap += ("a" -> "b")
    println(treeMap)

  }

}

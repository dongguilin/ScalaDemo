package dt.scala.basics

/**
 * Created by hadoop on 2015/11/15.
 * Type与Class
 */

class Spark

trait Hadoop

object Flink

class Java {

  class Scala

}

object TypeAndClass {

  def main(args: Array[String]) {

    import scala.reflect.runtime.universe._

    println(typeOf[Spark]) //dt.scala.basics.Spark
    println(classOf[Spark]) //class dt.scala.basics.Spark

    println(typeOf[Hadoop]) //dt.scala.basics.Hadoop
    println(classOf[Hadoop]) //interface dt.scala.basics.Hadoop

    println(Flink.getClass) //class dt.scala.basics.Flink$

    val java1 = new Java
    val java2 = new Java
    val scala1 = new java1.Scala
    val scala2 = new java2.Scala
    println(scala1.getClass) //class dt.scala.basics.Java$Scala
    println(scala2.getClass) //class dt.scala.basics.Java$Scala
    println(typeOf[java1.Scala] == typeOf[java2.Scala]) //false
    println(typeOf[java1.Scala]) //java1.Scala
    println(typeOf[java2.Scala]) //java2.Scala

    println(classOf[List[Int]] == classOf[List[String]]) //true
    println(typeOf[List[Int]] == typeOf[List[String]]) //false

    println(classOf[List[Int]], classOf[List[String]]) //(class scala.collection.immutable.List,class scala.collection.immutable.List)
    println(typeOf[List[Int]], typeOf[List[String]]) //(List[Int],List[String])

  }

}

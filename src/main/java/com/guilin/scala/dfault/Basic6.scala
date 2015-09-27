package com.guilin.scala.dfault

/**
 * Created by hadoop on 2014/11/22.
 */
class Basic6 {

}

class A {

}

class RichA(a: A) {
  def rich {
    println("rich...")
  }
}

object Basic6 extends App {
  implicit def a2RichA(a: A) = new RichA(a)

  val a = new A
  a.rich

  def testParam(implicit name: String) {
    println(name)
  }

  implicit val name1 = "implicit!!!"
  testParam
  testParam("xx")

  implicit class Calculator(x: Int) {
    def add(a: Int): Int = a + 1
  }

  println(1.add(1))
  println(Calculator(1).add(3))


}

package com.guilin.scala.dfault

/**
 * Created by hadoop on 2014/10/31.
 */
class Basic4 {

}

class ApplyTest {
  def apply() = "APPLY"

  def test {
    println("test")
  }
}

object ApplyTest {

  def apply() = new ApplyTest

  def static {
    println("I am a static method")
  }

  var count = 0

  def incr = {
    count = count + 1
  }

}

object Basic4 extends App {

  ApplyTest.static

  //  val a = ApplyTest()
  //  a.test

  //  val a2 = new ApplyTest
  //  println(a2())
  //  println(a2)

  for (i <- 1 to 20) {
    ApplyTest.incr
  }
  println(ApplyTest.count)
}

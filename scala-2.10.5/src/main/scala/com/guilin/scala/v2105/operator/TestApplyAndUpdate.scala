package com.guilin.scala.v2105.operator

import org.scalatest.FunSuite

import scala.collection.mutable

/**
  * Created by Administrator on 2017/4/9.
  * apply和update方法
  * <p>
  * 1.f(arg1,arg2,...)如果f不是函数或方法，那么这个表达式等同于调用f.apply(arg1,arg2,...)，它出现在赋值语句
  * 等号左侧时f(arg1,arg2,...)=value等同于调用f.update(arg1,arg2,...,value)，这个机制被用于数组和映射<br>
  * 2.apply应用于伴生对象
  * </p>
  */
class TestApplyAndUpdate extends FunSuite {

  test("apply&update 映射") {
    val scores = mutable.HashMap[String, Int]()
    scores("Bob") = 100 //调用scores.update("Bob",100)
    scores.update("jack", 200)
    assertResult("Map(Bob -> 100, jack -> 200)")(scores.toString())

    val bobScore = scores("Bob") //调用scores.apply("Bob")
  }

  test("apply&update 数组") {
    val arr = new Array[Int](5)
    arr(1) = 3 //调用arr.update(1,3)
    arr.update(2, 4)

    val a = arr(2) //调用arr.apply(2)
    assertResult(4)(a)
    assertResult(4)(arr.apply(2))
  }

  test("apply应用于伴生对象，用来构造对象") {
    val result = Fraction(3, 4) * Fraction(2, 5)
    println(result)
  }

  class Fraction(n: Int, d: Int) {

    def *(fraction: Fraction) = {
      Fraction(getN() * fraction.getN(), getD() * fraction.getD())
    }

    override def toString = s"Fraction($getN, $getD)"

    def getN() = {
      n
    }

    def getD() = {
      d
    }
  }

  object Fraction {
    def apply(n: Int, d: Int): Fraction = new Fraction(n, d)
  }

}

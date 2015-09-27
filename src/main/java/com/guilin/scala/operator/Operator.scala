package com.guilin.scala.operator

import scala.collection.mutable

/**
 * Created by hadoop on 2015/8/11.
 */
object Operator {

  class Fraction(n: Int, d: Int) {
    private val num = n
    private val den = d

    //定义*操作符
    def *(other: Fraction) = {
      new Fraction(num * other.num, den * other.den)
    }

    //定义=操作符
    def +=(other: Fraction) = {
      new Fraction(num + other.num, den + other.den)
    }

    object Fraction {
      def apply(n: Int, d: Int) = new Fraction(n, d)
    }

    override def toString: String = {
      num + "," + den
    }
  }

  def main(args: Array[String]) {

    /*
    标识符
     */

    //使用反引号包含特殊字符序列
    val `val` = 42
    println(`val`)

    //yield是scala中的关键字，加反引号使用java中的方法
    Thread.`yield`()


    /*
    中置操作符
     */

    1 to 10
    1.to(10)

    1 -> 10
    1.->(10)

    println(new Fraction(2, 3) * (new Fraction(3, 4)))


    /*
    一元操作符
     */

    //后置(postfix)操作符
    println(1 toString, 1 toString(), 1.toString, 1.toString())

    //前置(prefix)操作符 (+、-、！、~)
    println(+2, -2, !(1 > 3), ~2)
    println(2 unary_+, 2.unary_-, (1 > 3) unary_!, 2 unary_~)


    /*
    赋值操作符（a 操作符= b  等同于 a = a 操作符 b）
     */
    //<=、>=、!=、===、==、=/=不是赋值操作符

    println(new Fraction(2, 3) += new Fraction(3, 4))


    /*
    apply和update方法
     */
    val scores = new mutable.HashMap[String, Int]
    scores("Bob") = 100 //调用scores.update("Bob",100)
    val bobsScore = scores("Bob") //调用scores.apply("Bob")
    println(bobsScore) //100
    println(scores("Jack"))


  }

}

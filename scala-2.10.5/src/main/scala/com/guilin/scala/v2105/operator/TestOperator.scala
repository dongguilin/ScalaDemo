package com.guilin.scala.v2105.operator

import org.scalatest.FunSuite

/**
  * Created by Administrator on 2017/4/9.
  * 操作符
  */
class TestOperator extends FunSuite {

  //变量、函数、类等的名称统称为标识符
  test("标识符") {
    //可以在反引号中包含几乎任何字符序列
    //在scala中val、yield是保留字，不过可以用反引号包含来使用
    val `val` = 42
    Thread.`yield`()
  }

  //中置操作符是二元的，一个隐式的参数和一个显式的参数
  test("中置操作符") {
    1 to 10
    1.to(10)
    assertResult((1, 10))(1 -> 10)
    assertResult((1, 10))(1.->(10))

    class Fraction(n: Int, d: Int) {
      private val num = n
      private val den = d

      def *(other: Fraction) = new Fraction(num * other.num, den * other.den)

      //wrong
      //    def *(other: Fraction) = new Fraction(n * other.n, d * other.d)

      override def toString = s"Fraction($num, $den)"
    }

    println(new Fraction(2, 3) * new Fraction(3, 4))
  }

  test("一元操作符") {
    //后置操作符，参数在标识符前
    //四个等同
    println(1 toString)
    println(1 toString())
    println(1.toString())
    println(1.toString)

    //+、-、！、~可以作为前置操作符，参数在标识符后
    val a = 12
    //两者等同
    assertResult(-12)(-a)
    assertResult(-12)(a.unary_-)
  }

  /**
    * 1.形式为：操作符=
    * 2.a 操作符=b 等同于 a = a 操作符 b
    * 3.<=、>=、!=不是赋值操作符
    * 4.以=开头的操作符不是赋值操作符(==、===、=/=等)
    * 5.如果a有一个名为操作符=的方法，那么该方法会被直接调用
    */
  test("赋值操作符") {
    var a = 12
    a += 3

    class Hello {
      def `=`() = {
        "hello"
      }

      def +=(str: String): String = {
        this.`=`() + str
      }

      def +=(): String = {
        this.`=`()
      }
    }

    val b = new Hello()
    assertResult("hello")(b `=`)
    assertResult("hello张三")(b += "张三")
    assertResult("hello")(b +=)
  }

  //后置操作符优先级低于中置操作符
  test("优先级") {
    //a 中置操作符 b 后置操作符

    println(3 + 4 -> 5)
    println(3 -> (4 + 5))
  }

  //scala中，所有操作符都是左结合的，除了：1.以冒号:结尾的操作符; 2.赋值操作符; 3.用于构造列表的::操作符
  test("结合性") {

    1 :: 2 :: Nil //等同于 1 :: (2 :: Nil)

    //右结合的二元操作符是其第二个参数的方法
    2 :: Nil //意思是 Nil.::(2)
    println(2 :: Nil)
    println(Nil.::(2))
  }

}

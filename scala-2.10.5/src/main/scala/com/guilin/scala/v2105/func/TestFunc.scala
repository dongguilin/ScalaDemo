package com.guilin.scala.v2105.func

import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.JButton

import org.scalatest.FunSuite

import scala.math._

/**
  * Created by Administrator on 2017/4/22.
  */
class TestFunc extends FunSuite {

  test("作为值的函数") {
    val num = 3.14
    val fun = ceil _ //_将ceil方法转成了函数
    assertResult(4)(fun(num))
    val arr = Array(3.14, 1.42, 2.1, 2.0).map(fun)
    assertResult("List(4.0, 2.0, 3.0, 2.0)")(arr.toList.toString)
  }

  test("匿名函数") {
    (x: Double) =>
      3 * x //匿名函数
    val triple = (x: Double) => 3 * x //将函数存放到变量中
    val arr = Array(3.14, 1.42, 2.1, 2.0)
      arr.map((x: Double) => 3 * x)
      arr.map { (x: Double) => 3 * x }
      arr map ((x: Double) => 3 * x)
      arr map { (x: Double) => 3 * x }
  }

  test("带函数参数的函数") {
    def valueAtOneQuarter(f: (Double) => Double) = f(0.5)

    assertResult(1.0)(valueAtOneQuarter(ceil _))
    println(valueAtOneQuarter(sqrt))
    println(valueAtOneQuarter(sqrt _))
  }

  test("参数(类型)推断") {
    val fun = 3 * (_: Double)
    val fun2: (Double) => Double = 3 * _
    val valueAtOneQuarter = (f: Double) => Double
  }

  test("一些有用的高阶函数") {
    (1 to 9) map ("*" * _) foreach (println _)
    (1 to 9) filter (_ % 2 == 0) //2,4,6,8
    (1 to 9) reduceLeft (_ * _)
    "Mary has a little lamb".split(" ").sortWith(_.length < _.length)
  }

  /**
    * 在scala内，可以在任何作用域内定义函数:包、类甚至是另一个函数或方法
    * 闭包由代码和代码用到的任何非局部变量定义构成
    */
  test("闭包") {
    //mulBy实际上是以类的对象方式实现的，该类有一个实例变量factor和一个包含了函数体的apply方法
    def mulBy(factor: Double) = (x: Double) => factor * x

    val triple = mulBy(3) //函数,factor被设置为3
    assertResult(42)(triple(14))
    val half = mulBy(0.5) //函数,factor被设置为0.5
    assertResult(7)(half(14))

    def fun1(a: Double) = (x: String) => x + (a * 2).toString

    val a = fun1(2)
    assertResult("hello4.0")(a("hello"))
  }

  test("SAM转换") {
    var counter = 0
    val button = new JButton("Increment")
    button.addActionListener(new ActionListener {
      override def actionPerformed(e: ActionEvent): Unit = {
        counter += 1
      }
    })

    implicit def makeAction(action: ActionEvent => Unit) = new ActionListener {
      override def actionPerformed(e: ActionEvent): Unit = action(e)
    }

    button.addActionListener((event: ActionEvent) => counter += 1)

  }

  /**
    * 柯里化指将原来接受两个参数的函数变成新的接受一个参数的函数的过程。新的函数返回一个以原有第二个参数作为参数的函数
    */
  test("柯里化") {
    def mulOneAtATime(x: Int) = (y: Int) => x * y

    mulOneAtATime(6)(7) //mulOneAtATime(6)的结果是函数(y:Int)=>6*y


    //mulOneAtATime等价于mulOneAtBTime柯里化函数
    def mulOneAtBTime(x: Int)(y: Int) = x * y

    val a = Array("hello", "world")
    val b = Array("Hello", "World")
    assertResult(true)(a.corresponds(b)(_.equalsIgnoreCase(_)))
    a.corresponds(b)((m, n: String) => {
      println(m, n)
      true
    })
  }

  /**
    * 在scala中，我们可以将一系列语句归组成不带参数也没有返回值的函数
    */
  test("控制抽象") {
    def runInThread(block: () => Unit) {
      new Thread {
        override def run() {
          block()
        }
      }.start()
    }

    runInThread { () =>
      println("Hi");
      Thread.sleep(1000);
      println("Bye")
    }

    //在调用中省掉()=>，可以使用换名调用表示法：在参数声明和调用该参数的地方略去()，但保留=>
    def runInThread2(block: => Unit) {
      new Thread {
        override def run() {
          block
        }
      }.start
    }

    runInThread2 {
      println("Hi");
      Thread.sleep(1000);
      println("Bye")
    }

    //定义一个until语句
    def until(condition: => Boolean)(block: => Unit) {
      if (!condition) {
        block
        until(condition)(block)
      }
    }

    var x = 10
    until(x == 0) {
      x -= 1
      println(x)
    }
  }

  test("return 表达式") {
    def indexOf(str: String, ch: Char): Int = {
      def until(condition: => Boolean)(block: => Unit) {
        if (!condition) {
          block
          until(condition)(block)
        }
      }

      var i = 0
      until(i == str.length) {
        if (str(i) == ch) return i
        i += 1
      }
      return -1
    }

    assertResult(5)(indexOf("helloda", 'd'))
  }

}

package com.guilin.scala.dfault

/**
 * Created by hadoop on 2014/10/31.
 */
object Basic {

  def hello(name: String = "ChinaSpark"): String = {
    "Hello:" + name
  }

  def helloScala() {
    println("hello Scala!!!")
  }

  //匿名函数
  def add = (x: Int, y: Int) => x + y

  //颗粒化
  def add2(x: Int)(y: Int) = x + y

  def printEveryChar(c: String*) = {
    //    c foreach(x => print(x))
    //    c.foreach(x => print(x))
    c foreach (print(_))
  }

  def main(args: Array[String]) = {
    //    println("HelloWorld!")

    //使用默认值
    println(hello()) //Hello:ChinaSpark

    println(hello("Scala")) //Hello:Scala

    //两种方式相同
    //无参数时可不带括号
    println(helloScala) //hello Scala!!! \n ()
    println(helloScala()) //hello Scala!!! \n ()

    println(add(1, 2)) //3

    println(add2(1)(3)) //4

    printEveryChar("a", "b", "c", "d", "\n") //abcd

    //if... else...
    val x = 1
    val a = if (x > 0) 1 else 0
    println(a) //1

    //while循环
    var (n, r) = (10, 0) //同时为多个变量赋值
    while (n > 0) {
      r = r + n
      n = n - 1
    }
    println(r) //0+1+2+...+10=5*11=55

    //for循环
    for (i <- 1 to 10) {
      print(i + " ") //输出1-10
    }
    println()

    for (i <- 1.to(10)) {
      print(i + " ") //输出1-10
    }
    println()

    for (i <- 1 until 10) {
      print(i + " ") //输出1-9
    }
    println()

    for (i <- 1 to 10 if i % 2 == 0) {
      print(i + " ") //打印偶数
    }

  }

}

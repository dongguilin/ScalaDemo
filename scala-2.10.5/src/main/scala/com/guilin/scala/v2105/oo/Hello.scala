package com.guilin.scala.v2105.oo

/**
  * Created by Administrator on 2017/4/29.
  * 应用程序对象
  * 每个scala程序都必须从一个对象的main方法开始
  */
object HelloApp extends App {
  if (args.length > 0) println("Hello, " + args(0))
  else println("Hello, World!")
}

object Hello {
  def main(args: Array[String]): Unit = {
    println("Hello, World!")
  }
}

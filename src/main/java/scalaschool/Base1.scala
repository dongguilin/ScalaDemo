package scalaschool

/**
 * Created by guilin1 on 15/8/1.
 * 基础
 * 1.值
 * 2.变量
 * 3.函数
 * 4.匿名函数
 * 5.部分应用
 * 6.柯里化函数
 * 7.可变长度参数
 */
object Base1 {

  /*
  为什么选择 Scala?
    表达能力
      函数是一等公民
      闭包
    简洁
      类型推断
      函数创建的文法支持
    Java互操作性
      可重用Java库
      可重用Java工具
      没有性能惩罚
  Scala 如何工作?
    编译成Java字节码
    可在任何标准JVM上运行
    甚至是一些不规范的JVM上，如Dalvik
    Scala编译器是Java编译器的作者写的
   */

  def main(args: Array[String]) {

    //值
    val two = 1 + 1


    //变量
    var name = "Tom"
    name = "Jack"


    //函数
    def addOne(m: Int): Int = m + 1
    def addOne2(m: Int) = m + 1
    def addOne3(m: Int) = {
      m + 1
    }
    println(addOne(1), addOne2(1), addOne3(1)) //(2,2,2)

    def three() = 1 + 2
    println(three(), three) //(3,3)


    //匿名函数
    val addOne4 = (m: Int) => m + 1
    //    (m: Int) => {
    //      m + 1
    //      println(m + 1)
    //    }
    //    {
    //      m: Int =>
    //        println(m + 1)
    //        m + 1
    //    }


    //部分应用(Partial Application)
    //你可以使用下划线“_”部分应用一个函数，结果将得到另一个函数。Scala使用下划线表示不同上下文中的不同事物，你通常可以把它看作是一个没有
    // 命名的神奇通配符。在{ _ + 2 }的上下文中，它代表一个匿名参数。你可以这样使用它：
    def adder(m: Int, n: Int, o: Int) = m + n + o
    val add2 = adder(2, _: Int, _: Int)
    println(add2(3, 4)) //9


    //柯里化函数(允许别人一会在你的函数上应用一些参数，然后又应用另外的一些参数)
    def multiply(m: Int)(n: Int) = m * n
    println(multiply(2)(3)) //6
    //可以填上第一个参数并且部分应用第二个参数
    def timesTwo = multiply(2) _
    println(timesTwo(3)) //6


    //可变长度参数(可以向方法传入任意多个同类型的参数)

    def capitalizeAll(args: String*) = {
      //      args.map(_.capitalize)
      args.map(arg => arg.capitalize)
    }
    println(capitalizeAll("hello", "world", "hehe", "嘿嘿")) //ArrayBuffer(Hello, World, Hehe, 嘿嘿)


  }


}

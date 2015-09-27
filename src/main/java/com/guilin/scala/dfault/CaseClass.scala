package com.guilin.scala.dfault

/**
 * Created by hadoop on 2015/9/15.
 * Scala支持case类的概念。case类也是正规的类，暴露其构造器参数，并通过模式匹配提供递归解构机制
 */
object CaseClass {

  abstract class Term

  case class Var(name: String) extends Term

  case class Fun(arg: String, body: Term) extends Term

  case class App(f: Term, v: Term) extends Term

  def main(args: Array[String]) {

    //case类的构造器参数被当作公开值并可以直接被访问。
    val v = Var("hello")
    println(v.name)

    //对于每个case类，Scala编译器将产生实现了结构相等性检查的equals方法和一个toString方法
    val x1 = Var("x")
    val x2 = Var("x")
    val y1 = Var("y")
    println("" + x1 + " == " + x2 + " => " + (x1 == x2)) //Var(x) == Var(x) => true
    println("" + x1 + " == " + y1 + " => " + (x1 == y1)) //Var(x) == Var(y) => false

    //只有在分解数据结构的时候用到了模式匹配的情况下，定义case类才是有意义的。以下的对象为我们的lambda算子的展示定义了漂亮的打印函数：
    def printTerm(term: Term) {
      term match {
        case Var(n) =>
          print(n)
        case Fun(x, b) =>
          print("^" + x + ".")
          printTerm(b)
        case App(f, v) =>
          Console.print("(")
          printTerm(f)
          print(" ")
          printTerm(v)
          print(")")
      }
    }

    def isIdentityFun(term: Term): Boolean = term match {
      case Fun(x, Var(y)) if x == y => true
      case _ => false
    }
    val id = Fun("x", Var("x"))
    val t = Fun("x", Fun("y", App(Var("x"), Var("y"))))
    printTerm(t)
    println
    println(isIdentityFun(id))
    println(isIdentityFun(t))


  }

}

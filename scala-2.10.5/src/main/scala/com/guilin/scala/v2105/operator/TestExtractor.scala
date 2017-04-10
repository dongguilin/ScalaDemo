package com.guilin.scala.v2105.operator

import org.scalatest.FunSuite

/**
  * Created by Administrator on 2017/4/9.
  * 提取器
  * <p>
  * 1.提取器就是一个带有unapply方法的对象，可以把unapply方法当做是伴生对象中apply方法的反向操作
  * 2.每一个样例类都自动具备apply和unapply方法
  * 3.unapplySeq提取任意长度的序列
  * </p>
  */
class TestExtractor extends FunSuite {

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

  //样例类自动具备apply和unapply方法
  case class Currency(value: Double, unit: String)

  case class Person(name: String, age: Int)

  test("unapply") {
    val Fraction(a, b) = Fraction(3, 4) * Fraction(2, 5)
    assertResult((6, 20))((a, b))
  }

  test("unapply Name") {
    val author = "dong guilin"
    val Name(first, last) = author
    assertResult("dong")(first)
    assertResult("guilin")(last)
  }

  object Fraction {
    def apply(n: Int, d: Int): Fraction = new Fraction(n, d)

    def unapply(input: Fraction): Option[(Int, Int)] = {
      if (input.getD() == 0) None else Some((input.getN(), input.getD()))
    }
  }

  //Name对象是针对String对象的一个提取器
  object Name {
    def unapply(input: String): Option[(String, String)] = {
      val pos = input.indexOf(" ")
      if (pos == -1) None else Some((input.substring(0, pos), input.substring(pos + 1)))
    }

  }

  test("case class") {
    val obj: Object = Person("张三", 12)
    assertResult("张三 12") {
      obj match {
        case Currency(amount, "USD") => {
          println("$" + amount)
          amount
        }
        case Person(name, age) => {
          name + " " + age
        }
        case _ => "something else"
      }
    }
  }

  object Number {
    def unapply(arg: String): Option[Int] = {
      try {
        Some(Integer.parseInt(arg.trim))
      } catch {
        case ex: Exception => {
          println(ex.getMessage)
          None
        }
      }
    }
  }

  object IsCompound {
    def unapply(arg: String): Boolean = {
      arg.contains(" ")
    }
  }

  test("@测试输入") {
    val currency: Object = Currency(11, "hello zhangsan ")
    assertResult("unit has blank str")(currency match {
      case Currency(value, unit@IsCompound()) => "unit has blank str"
      case Currency(value, unit) => "unit has no blank str"
      case _ => "something else"
    })

    assertResult("unit has no blank str")(Currency(1, "张") match {
      case Currency(value, unit@IsCompound()) => "unit has blank str"
      case Currency(value, unit) => "unit has no blank str"
      case _ => "something else"
    })
  }


  test("带单个参数或无参数的提取器") {
    val Number(n) = "123"
    assertResult(123)(n)
  }

  object Students {
    def unapplySeq(arg: String): Option[Seq[String]] = {
      if (arg.trim == "") None else Some(arg.trim.split("\\s+"))
    }
  }

  test("unapplySeq") {
    val obj: Object = "张三 李四 hello tom"
    assertResult("four") {
      obj match {
        case Students(n1, n2) => "two"
        case Students(n1, n2, n3) => "three"
        case Students("张三", n2, n3, n4) => "four"
        case _ => "something else"
      }
    }
  }

  //抽取文件的路径、文件名、文件后缀名
  test("unapply RichFile") {
    class RichFile(val path: String) {}
    object RichFile {
      def apply(path: String): RichFile = new RichFile(path)

      def unapply(arg: RichFile): Option[(String, String, String)] = {
        if (arg.path == null) None
        else {
          val reg = "([/\\w+]+)/(\\w+)\\.(\\w+)".r
          val reg(r1, r2, r3) = arg.path
          Some((r1, r2, r3))
        }
      }
    }

    val richFile = RichFile("/home/cay/readme.txt")
    val RichFile(path, name, suffix) = richFile
    assertResult("/home/cay")(path)
    assertResult("readme")(name)
    assertResult("txt")(suffix)

  }

  //抽取路径段
  test("unapplySeq RichFile") {
    class RichFile(val path: String) {}
    object RichFile {
      def apply(path: String): RichFile = new RichFile(path)

      def unapplySeq(richFile: RichFile): Option[Seq[String]] = {
        if (richFile.path == null) None
        else {
          var path = richFile.path.trim
          if (path.trim.charAt(0) == '/') path = path.substring(1)
          Some(path.split("/"))
        }
      }
    }

    val richFile = RichFile("/home/cay/readme.txt/")
    val RichFile(r@_*) = richFile
    assertResult("WrappedArray(home, cay, readme.txt)")(r.toString())

    try {
      val RichFile(r@_*) = RichFile(null)
    } catch {
      case ex: MatchError => {
        ex.getMessage()
      }
    }
  }


}

package com.guilin.scala.v2105.patternmatch

import java.awt.Color

import org.scalatest.FunSuite

import scala.swing.Color

/**
  * Created by Administrator on 2017/4/8.
  * 模式匹配
  * <p>
  * 1.模式总是从上往下进行匹配，与switch语句不同，scala模式匹配不会有“意外掉入到下一个分支”的问题<br>
  * 2.匹配发生在运行期，Java虚拟机中泛型的类型信息是被擦掉的<br>
  * 3.match也是表达式<br>
  * 4.可以在match表达式中使用任何类型<br>
  * 5.匹配类型的时候，必须给出一个变量名，否则，将会拿对象本身来进行匹配<br>
  * 6.模式中可以有守卫，守卫可以是任何Boolean条件<br>
  * 7.scala区分模式中的变量与常量规则：变量必须以小写字母开头，如果有一个小写字母开头的常量，则需要将它包在反引号中<br>
  * </p>
  */
class TestMatch extends FunSuite {

  test("match") {
    var sign = 11
    val ch: Char = '-'
    ch match {
      case '+' => sign = 1
      case '-' => sign = -1
      case _ => sign = 0
    }
    assertResult(-1)(sign)
  }

  test("match也是表达式") {
    val ch: Char = '-'
    var sign = ch match {
      case '+' => 1
      case '-' => -1
      case _ => 0
    }
    assertResult(-1)(sign)
    assertResult(-1)(ch match {
      case '+' => 1
      case '-' => -1
      case _ => 0
    })
  }

  test("可以在match表达式中使用任何类型") {
    val color: Color = Color.BLUE
    assertResult("it's blue")(color match {
      case Color.RED => "it's red"
      case Color.BLUE => "it's blue"
      case _ => "other color"
    })
  }

  /**
    * 守卫&模式中的变量
    * 守卫可以是任何Boolean条件
    * scala区分模式中的变量与常量规则：变量必须以小写字母开头，如果有一个小写字母开头的常量，
    * 则需要将它包在反引号中
    */
  test("守卫&模式中的变量") {
    //ASCII码中48-57对应数字0-9
    val sign = 50
    val ch = 'h'
    assert(Character.isDigit(sign))
    assert(!Character.isDigit(2))
    assertResult(2)(sign match {
      case '+' => 1
      case '-' => -1
      //如果case关键字后面跟着一个变量名，那么匹配的表达式会被赋值给那个变量，变量必须以小写字母开头
      case ch if Character.isDigit(ch) => Character.digit(ch, 10)
      //小写字母开头的常量,则需要将它包在反引号中
      case `ch` => 10
      case _ => 0
    })
  }

  test("类型模式") {
    val obj: Object = BigInt.apply(12)
    assertResult(Int.MaxValue)(obj match {
      case x: Integer => x
      case s: String => Integer.parseInt(s)
      case _: BigInt => Int.MaxValue
      case _ => 0
    })

    //匹配类型的时候，必须给出一个变量名，否则，将会拿对象本身来进行匹配
    val obj2: Object = BigInt //BigInt伴生对象
    println(obj2.getClass)
    assertResult(-1)(obj2 match {
      case _: BigInt => Int.MaxValue //匹配任何类型为BigInt的对象
      case BigInt => -1 //匹配类型为Class的BigInt对象
      case _ => 0
    })
  }

  test("匹配数组") {
    var arr = Array(0)
    assertResult("0")(arr match {
      case Array(0) => "0" //匹配包含0的数组，且仅有一个0
      case Array(x, y) => x + " " + y //匹配任何有且仅有两个元素的数组
      case Array(0, _*) => "0 ..." //匹配任何以0开始的数组
      case _ => "something else"
    })

    arr = Array(0, 3)
    assertResult("0,3")(arr match {
      case Array(0) => "0"
      case Array(0, 3) => "0,3" //有两个元素0,3的数组
      case Array(x, y) => x + " " + y
      case Array(0, _*) => "0 ..."
      case _ => "something else"
    })

    arr = Array(2, 3)
    assertResult("2 3")(arr match {
      case Array(0) => "0"
      case Array(0, 3) => "0,3"
      case Array(x, y) => x + " " + y //匹配任何有且仅有两个元素的数组
      case Array(0, _*) => "0 ..."
      case _ => "something else"
    })

    arr = Array(0, 2, 3)
    assertResult("0 ...")(arr match {
      case Array(0) => "0"
      case Array(x, y) => x + " " + y
      case Array(0, _*) => "0 ..." //匹配任何以0开始的数组
      case _ => "something else"
    })

    arr = Array(2, 3, 4)
    assertResult("something else") {
      arr match {
        case Array(0) => "0"
        case Array(x, y) => x + " " + y
        case Array(0, _*) => "0 ..."
        case _ => "something else"
      }
    }
  }

  test("匹配列表") {
    var list: List[Integer] = List()
    assertResult("it's Nil") {
      list match {
        case Nil => "it's Nil"
        case tail => "tail"
        case List(a) => "List(a)"
        case x :: y :: Nil => x + " " + y
        case _ => "something else"
      }
    }

    list = null
    assertResult("tail") {
      list match {
        case tail => "tail"
        case Nil => "it's Nil"
        case List(a) => "List(a)"
        case x :: y :: Nil => x + " " + y
        case _ => "something else"
      }
    }

    list = List()
    assertResult("tail") {
      list match {
        case tail => "tail"
        case Nil => "it's Nil"
        case List(a) => "List(a)"
        case x :: y :: Nil => x + " " + y
        case _ => "something else"
      }
    }

    list = List(12)
    assertResult("List(a)") {
      list match {
        case Nil => "it's Nil"
        case List(a) => "List(a)"
        case x :: y :: Nil => x + " " + y
        case _ => "something else"
      }
    }

    list = List(12, 13)
    assertResult("12 13") {
      list match {
        case Nil => "it's Nil"
        case List(a) => "List(a)"
        case x :: y :: Nil => x + " " + y
        case _ => "something else"
      }
    }

    list = List(12, 13, 14)
    assertResult("something else") {
      list match {
        case Nil => "it's Nil"
        case List(a) => "List(a)"
        case x :: y :: Nil => x + " " + y
        case _ => "something else"
      }
    }
  }

  test("匹配无组") {
    var pair: Object = (2, 3)

    assertResult("(2,3)") {
      pair match {
        case (2, b) => "(2," + b + ")"
        case (x, 3) => x + " 3"
        case (2, _, _, _) => "four elements"
        case _ => "something else"
      }
    }

    pair = ("a", 3)
    assertResult("a 3") {
      pair match {
        case (2, b) => "(2," + b + ")"
        case (x, 3) => x + " 3"
        case (2, _, _, _) => "four elements"
        case _ => "something else"
      }
    }

    pair = (2, 3, 4, 5)
    assertResult("four elements") {
      pair match {
        case (2, b) => "(2," + b + ")"
        case (x, 3) => x + " 3"
        case (2, _, _, _) => "four elements"
        case _ => "something else"
      }
    }

    pair = (2, 3, 4)
    assertResult("something else") {
      pair match {
        case (2, b) => "(2," + b + ")"
        case (x, 3) => x + " 3"
        case (2, _, _, _) => "four elements"
        case _ => "something else"
      }
    }
  }

}

package com.guilin.scala.v2105.oo

import org.scalatest.FunSuite


/**
  * Created by Administrator on 2017/4/29.
  */
class TestImport extends FunSuite {

  test("任何地方都可以声明引入") {
    import scala.collection.mutable.ArrayBuffer
    val subordinates = new ArrayBuffer[String]
  }

  test("引入包中的几个成员") {
    import java.awt.{Color, Font}
    val color = Color.BLACK
    val font = Font.BOLD
  }

  test("重命名") {
    import java.util.{HashMap => JavaHashMap}
    val a = new JavaHashMap[String, Integer]()
    a.put("hello", 1)
    a.put("world", 2)
    assertResult("{world=2, hello=1}")(a.toString)

    import scala.collection.mutable._
    val b = new HashMap[String, Integer]
    b += ("hello" -> 1)
    b += ("world" -> 2)
    assertResult("Map(world -> 2, hello -> 1)")(b.toString())
  }

  test("隐藏") {
    import java.util.{HashMap => _}

    import scala.collection.mutable._
    val b = new HashMap[String, Integer]
    b += ("hello" -> 1)
    b += ("world" -> 2)
    assertResult("Map(world -> 2, hello -> 1)")(b.toString())
  }

}

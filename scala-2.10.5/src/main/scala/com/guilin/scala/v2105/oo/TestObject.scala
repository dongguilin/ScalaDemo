package com.guilin.scala.v2105.oo

import org.scalatest.FunSuite

/**
  * Created by Administrator on 2017/4/29.
  * 对象
  */
class TestObject extends FunSuite {


  /**
    * <pre>
    * scala没有静态方法或静态字段，可以用object这个语法结构来达到同样的目的
    * 对象的构造器在该对象第一次使用时调用
    * 对象本质上可以拥有类的所有特性，它可以扩展类或特质，只有一个例外，它不能提供构造器参数
    * 在scala中可以用对象来实现：
    *     1.作为存放工具函数或常量的地方
    *     2.高效地共享单个不可变实例
    *     3.需要用单个实例来协调某个服务时
    * </pre>
    */
  test("单例对象") {
    object Account {
      println("init ...")
      private var lastNumber = 0

      def newUniqueNumber() = {
        lastNumber += 1
        lastNumber
      }
    }

    println(Account.newUniqueNumber())
    println(Account.newUniqueNumber())
  }


  /**
    * 类和它的伴生对象可以相互访问私有特性，它们必须存在于同一个源文件中
    */
  test("伴生对象") {
    class Account {
      val id = Account.newUniqueNumber()
      private var balance = 0.0

      def deposit(amount: Double) {
        balance += amount
      }
    }

    object Account {
      println("init ...")
      private var lastNumber = 0

      def newUniqueNumber() = {
        lastNumber += 1
        lastNumber
      }
    }
  }

  test("扩展类或特质的对象") {
    abstract class UndoableAction(val description: String) {
      def undo(): Unit

      def redo(): Unit
    }

    object DoNothingAction extends UndoableAction("Do nothing") {
      override def undo(): Unit = {
        println("TODO undo")
      }

      override def redo(): Unit = {
        println("TODO redo")
      }
    }

    val actions = Map("open" -> DoNothingAction, "save" -> DoNothingAction)
    actions("open").undo()
    actions("save").undo()
  }

  test("apply方法") {
    class Account(val id: Int, initialBalance: Double) {
      private var balance = initialBalance
    }
    object Account {
      println("init ...")
      private var lastNumber = 0

      def newUniqueNumber() = {
        lastNumber += 1
        lastNumber
      }

      def apply(initialBalance: Double) = {
        new Account(newUniqueNumber(), initialBalance)
      }
    }

    val acct = Account(1000.0)
  }

  test("枚举") {
    object TrafficLightColor extends Enumeration {
      val Red, Yellow, Green = Value
    }

    for (c <- TrafficLightColor.values) println(c.id + ": " + c)
    assertResult(TrafficLightColor.Red)(TrafficLightColor(0))
    assertResult(TrafficLightColor.Yellow)(TrafficLightColor.withName("Yellow"))
  }
}



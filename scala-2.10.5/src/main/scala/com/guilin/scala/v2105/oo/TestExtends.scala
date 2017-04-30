package com.guilin.scala.v2105.oo

import org.scalatest.FunSuite

/**
  * Created by Administrator on 2017/4/29.
  * 继承
  * <pre>
  *   1.在scala中重写一个非抽象方法必须使用override修饰符
  *   2.与Java不同，Scala中protected的成员对于类所属的包而言，是不可见的
  *   3.Scala中，只有主构造器可以调用超类的主构造器
  * </pre>
  */
class TestExtends extends FunSuite {

  /**
    * <pre>
    * Scala                     Java
    *   obj.isInstanceOf[CI]      obj instanceof CI
    *   obj.asInstanceOf[CI]      (CI)obj
    * classOf[CI]               CI.class
    * 不过，与类型检查和转换相比，模式匹配通常是更好的选择
    * </pre>
    */
  test("类型检查和转换") {

  }

  /**
    * <pre>
    *   1.def只能重写另一个def
    *   2.val只能重写另一个val或不带参数的def
    *   3.var只能重写另一个抽象的var
    * </pre>
    */
  test("重写字段") {
    class Person(val name: String) {
      override def toString = getClass.getName + "[name=" + name + "]"

      var age: Int = _
      var sex: String = "nan"
    }
    class SecretAgent(codename: String) extends Person(codename) {
      override val name = "secret"
      override val toString = "secret"
      //wrong,var只能重写另一个抽象的var
      //override var age = 12
      //override var sex = "nv"
    }

    var agent = new SecretAgent("zhangsan")
    println(agent.name, agent.age, agent.sex)
  }

  test("匿名子类") {
    class Person(val name: String) {
      override def toString = getClass.getName + "[name=" + name + "]"
    }
    val alien = new Person("Fred") {
      def greeting = "Greetings, Earthling! My name is Fred."
    }

    def meet(p: Person {def greeting: String}) {
      println(p.name + "says: " + p.greeting)
    }

    meet(alien)
  }

  /**
    * 在子类中重写超类的抽象方法时，不需要使用override关键字
    */
  test("抽象类") {
    abstract class Person(val name: String) {
      def id: Int //没有方法体，这是一个抽象方法
    }
    class Employee(name: String) extends Person(name) {
      override def id: Int = name.hashCode //不需要override关键字
    }
  }

  /**
    * 在子类中重写超类的抽象字段时，不需要使用override关键字
    */
  test("抽象字段") {
    abstract class Person {
      //这是一个带有抽象的getter方法的抽象字段
      val id: Int
      //带有抽象的getter和setter方法
      var name: String
    }

    class Employee(val id: Int) extends Person {
      //子类有具体的id属性
      var name = "" //具体的name属性
    }

    //使用匿名类
    val fred = new Person {
      val id = 111
      var name = "fred"
    }
  }

  test("构造顺序和提前定义") {
    class Creature {
      val range: Int = 10
      val env: Array[Int] = new Array[Int](range)
    }
    class Ant extends {
      //提前定义
      override val range = 2
    } with Creature
  }

  /**
    * 在scala中，AnyRef的eq方法检查两个引用是否指向同一个对象，AnyRef的equals方法调用eq
    * equals方法参数类型为Any，定义equals时，同时也要定义hashCode，在计算哈希码时，只应使用那些你用来做相等性判断的字段
    */
  test("对象相等性") {

  }


}

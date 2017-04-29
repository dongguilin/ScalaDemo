package com.guilin.scala.v2105.oo

import java.util.Date

import org.scalatest.FunSuite

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Administrator on 2017/4/29.
  * 类
  * <pre>
  * 1.在scala中，类并不声明为public,scala源文件可以包含多个类，所有这些类都具有公有可见性
  * 2.scala类中的字段与java或c++中的字段不同，scala中的字段是一个私有字段，加上getter方法(对val字段而言)或者getter和setter方法(对var字段而言)
  * </pre>
  */
class TestClass extends FunSuite {

  test("简单类和无参方法") {
    class Counter {
      private var value = Int.MaxValue //必须初始化字段
      //方法默认是公有的
      def increment() {
        if (value < Int.MaxValue) value + 1 else value
      }

      def current() = value
    }

    val myCounter = new Counter //或new Counter()
    myCounter.increment()
    println(myCounter.current) //或myCounter.current()
    //推荐对改值器使用()，对取值器不使用()
  }


  test("带有getter和setter的属性") {
    class Person {
      //生成的getter方法为age，生成的setter方法为age_$eq
      var age = 0 //公有字段，scala生成面向JVM的类，age字段会变为私有
    }
    val fred = new Person
    println(fred.age) //将调用方法fred.age()
    fred.age = 21 //将调用fred.age=(21)
    println(fred.age)
  }

  /**
    * 如果字段是val，则只有getter方法被生成
    */
  test("只带getter的属性") {
    class Message {
      val timestamp = new Date()
    }

    println(new Message().timestamp)
  }

  test("对象私有字段") {
    class Counter {
      private var value = 0

      def increment() {
        value += 1
      }

      def isLess(other: Counter) = value < other.value //可以访问另一个对象的私有字段

      private[this] var value2 = 0 //对象私有的字段，scala不会生成getter或setter方法
    }
  }

  test("Bean属性") {
    import scala.reflect.BeanProperty
    class Person {
      //将生成四个方法：name:String、name_=(newValue:String):Unit、getName():String、setName(newValue:String):Unit
      @BeanProperty
      var name: String = _

      @BeanProperty
      var age: Int = _
    }

    val person = new Person
    person.setName("xiaoche")
    println(person.getName)

    class Student(@BeanProperty var name: String)
  }

  /**
    * 主构造器的参数直接放置在类名之后
    * 主构造器会执行类定义中的所有语句
    */
  test("主构造器") {
    class Person(val name: String, val age: Int) {
      println("Just constructed another person")

      def description = name + " is " + age + " years old"
    }

    //使用默认参数
    class Person2(val name: String = "", val age: Int = 0)

    class Person3(private var age: Int)

    class Person4(private[this] var age: Int)

    //构造参数也可以是普通的方法参数，不带val或var
    //name和age是不可变的，是对象私有的等同于private[this] val字段的效果，仅仅是一个可以被主构造器中的代码访问的普通参数
    //如果不带val或var的参数至少被一个方法所使用，它将被升格为字段
    class Person5(name: String, age: Int)
  }

  test("私有主构造器") {
    //用户必须通过辅助构造器来构造Person对象
    class Person private(val id: Int) {
      var name = ""

      def this(name: String) {
        this(22)
        this.name = name
      }
    }
    val p = new Person("xiaoche")
    println(p.id)
    println(p.name)
  }

  /**
    * 一个类如果没有显示定义主构造器，则自动拥有一个无参的主构造器
    * 辅助构造器的名称为this
    * 每一个辅助构造器都必须以一个对先前已定义的其他辅助构造器或主构造器的调用开始
    */
  test("辅助构造器") {
    class Person {
      private var name = ""
      private var age = 0

      //一个辅助构造器
      def this(name: String) {
        this() //调用主构造器
        this.name = name
      }

      //另一个辅助构造器
      def this(name: String, age: Int) {
        this(name) //调用前一个辅助构造器
        this.age = age
      }
    }
  }

  /**
    * 在scala中，几乎可以在任何语法结构中内嵌任何语法结构，可以在函数中定义函数，可以在类中定义类
    */
  test("嵌套类") {
    class Network(val name: String) {
      outer =>

      class Member(val name: String) {
        val contacts = new ArrayBuffer[Member]

        //在内嵌类中，可以通过外部类.this的方式来访问外部类的this引用
        def description = name + " inside " + Network.this.name

        //或者
        def description2 = name + " inside " + outer.name
      }

      private val memebers = new ArrayBuffer[Member]

      def join(name: String) = {
        val m = new Member(name)
        memebers += m
        m
      }
    }

    //chatter.Member和myFace.Member是不同的两个类
    val chatter = new Network("192.168.60.1")
    val myFace = new Network("192.168.50.1")

    //在各自的网络中添加成员
    val fred = chatter.join("Fred")
    val wilma = chatter.join("Wilma")
    fred.contacts += wilma

    val barney = myFace.join("Barney")
    //wrong，不能跨网添加成员
    //fred.contacts += barney

    println(barney.description)
    println(barney.description2)

  }


}

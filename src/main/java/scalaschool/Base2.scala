package scalaschool

/**
 * Created by guilin1 on 15/8/1.
 * 基础
 *
 */
object Base2 {


  def main(args: Array[String]) {

    //apply方法
    class Foo {
      val name = "zhangsan"
    }
    object FooMaker {
      def apply() = {
        new Foo
      }

      def getFoo() = {
        new Foo
      }

    }
    val foo = FooMaker()
    println(foo.name) //zhangsan
    println(FooMaker.getFoo().name) //zhangsan

    class Bar {
      def apply() = 0
    }
    val bar = new Bar
    println(bar()) //0


    //单例对象(单例对象用于持有一个类的唯一实例。通常用于工厂模式)
    object Timer {
      var count = 0

      def currentCount(): Long = {
        count += 1
        count
      }
    }
    println(Timer.currentCount()) //1
    //单例对象可以和类具有相同的名称，此时该对象也被称为“伴生对象”。我们通常将伴生对象作为工厂使用
    class Student(name: String) {
      val score = 100
    }

    object Student {
      def apply(name: String) = new Student(name)

      val age = 20
    }
    Student.age
    Student("lisi")
    //wrong
    //    Student("lisi").age
    Student("lisi").score


    //函数即对象


    //包
    //    package com.guilin.scala.Basic
    //    class Student(name: String) {
    //      val score = 100
    //    }


  }

}

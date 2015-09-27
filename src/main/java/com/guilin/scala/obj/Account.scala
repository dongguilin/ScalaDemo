package com.guilin.scala.obj

import scala.beans.BeanProperty
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
 * Created by hadoop on 2015/8/12.
 */
class Account private(val id: Int, initialBlance: Double) {

  private var blance = initialBlance


}

class BankAccount {

  //余额
  val balance = 100

  //存款
  def deposit(num: Int) = {
    balance + num
  }

  //收回
  def withdraw(num: Int) = {
    balance - num
  }
}

class Time(val hours: String, val minutes: Int) {

  val time1 = hours + ":" + minutes
  val time2 = hours * 60 + minutes

  def before(other: Time) = {
    time1 < other.time1
  }

  def before2(other: Time) = {
    time2 < other.time2
  }

}

//简单类和无参方法
class Counter {
  private var value = 0

  //必须初始化字段
  def increment() = {
    //方法默认是公有的
    value += 1
  }

  def increment(v: Int) = {
    value += v
    if (value < 0) {
      value += Math.abs(Int.MinValue)
    }
    value
  }

  def current = value
}


//getter和setter属性
class Person {
  //带getter和setter的属性
  //scala生成面向JVM的类，生成私有age字段和公有getter和setter方法，在Scala中，getter和setter分别叫做age和age_=
  var age = 20

  //变成私有并改名
  private var privHeight = 165

  def height = privHeight

  def height_=(height: Int) {
    if (height > privHeight)
      privHeight = height
  }

  //只带getter属性
  val country = "中国"

  private[this] var privValue = 0

  class Man {
    private[Person] var privName = "张三"
  }

}


//Bean属性
class Student {
  /*
  将生成4个方法：
  name:String
  name_=(newValue:String):Unit
  getName():String
  setName(newValue:String):Unit
   */
  @BeanProperty
  var name: String = _
}


//辅助构造器
class Dog {

  private var name = ""
  private var age = 0

  def this(name: String) {
    //一个辅助构造器
    this()
    this.name = name
  }

  def this(name: String, age: Int) {
    //另一个辅助构造器
    this(name) //调用前一个辅助构造器
    this.age = age
  }

}


//主构造器（会执行类定义中的所有语句）
class Teacher(val name: String, private var age: Int) {
  println("Just constructed another teacher")

  def description = name + " is " + age + " years old"
}

//可以使用默认参数
class Teacher2(val name: String = "zhangsan")

//不带val或var的参数至少被一个方法所使用，它将被升格为字段
//name、age变成对象私有,且为不可变字段，即private[this] val
class Teacher3(name: String, age: Int) {
  def description = name + " is " + age + " years old"
}


//嵌套类
class Network {

  class Member(val name: String) {
    val contacts = new ArrayBuffer[Member]
  }

  private val members = new ArrayBuffer[Member]

  def join(name: String) = {
    val m = new Member(name)
    members += m
    m
  }
}

//伴生对象
object Account {
  def apply(initialBlance: Double) = {
    new Account(newUniqueNumber, initialBlance)
  }

  def newUniqueNumber = {
    Random.nextInt(100)
  }

  def main(args: Array[String]) {

    println(Account(21.3).blance)
    Account(12.3)

    val myCounter = new Counter
    //    myCounter.increment()
    println(myCounter.current) //1
    println(myCounter.increment(Int.MaxValue + 3))
    println("--------------------------")

    val bankAccount = new BankAccount
    println(bankAccount.deposit(20)) //120
    println(bankAccount.withdraw(10)) //90
    println("---------------------------")

    val xiaoming = new Person
    println(xiaoming.age) //20
    xiaoming.age = 25
    println(xiaoming.age) //25
    xiaoming.height = 177
    println(xiaoming.height) //177
    xiaoming.height = 176
    println(xiaoming.height) //177
    println(xiaoming.country) //中国
    println("------------------------")

    val xiaowen = new Student
    println(xiaowen.getName, xiaowen.name) //(null,null)
    xiaowen.setName("xiaowen")
    println(xiaowen.getName, xiaowen.name) //(xiaowen,xiaowen)
    println("------------------------")


    val chatter = new Network
    val myFace = new Network
    val fred = chatter.join("Fred")
    val wilma = chatter.join("Wilma")
    fred.contacts += wilma
    val barney = myFace.join("Barney")
    //wrong
    //    fred.contacts+=barney
    println("-------------------------")


    val time1 = new Time("03", 13)
    val time2 = new Time("04", 13)
    println(time1.before2(time2)) //true

  }


}

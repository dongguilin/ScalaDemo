package com.guilin.scala.dfault

/**
 * Created by hadoop on 2014/10/31.
 */
class Basic3 {

}

abstract class Person1 {
  def speak

  val name: String
  var age: Int
}

class Student1 extends Person1 {
  def speak {
    println("speak!!!")
  }

  val name = "AAA"
  var age = 100
}

//trait 当作接口；带有具体实现的接口；带有特质的对象；特质从左到右被构造
trait ConsoleLogger {

  //带有具体实现
  def log(msg: String) {
    println("save monty " + msg)
  }
}

trait MessageLogger extends ConsoleLogger {
  override def log(msg: String) {
    println("save monty to bank:" + msg)
  }
}

abstract class Account {
  def save
}

class MyAccount extends Account with ConsoleLogger {
  def save {
    log("100")
  }
}


object Basic3 extends App {
  val s = new Student1
  s.speak
  println(s.name + "," + s.age)

  val acc = new MyAccount
  acc.save

  //带有特质的对象
  val acc2 = new MyAccount with MessageLogger
  acc2.save
}

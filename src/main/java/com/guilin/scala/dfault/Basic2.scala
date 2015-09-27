package com.guilin.scala.dfault

/**
 * Created by hadoop on 2014/10/31.
 */
class Basic2 {

}

class Person {
  var name: String = _ //会生成getter和setter方法

  val age = 10 //只会生成getter方法

  private[this] val gender = "male" //private[this]只能在内部使用

}

//1.主构造器直接放在类名后面；主构造器中的参数最后会被编译成字段
//2.主构造器执行的时候会执行类中的所有语句
//3.假设参数声明的时候不带val和var，那么相当与private[this]
class Person2(var name: String, val age: Int) {
  println("this is the primary contructor")

  var gender: String = _
  val school = "PDSU"

  //1.附属构造器名称为this
  //2.每一个附属构造器必须调用已经存在的子构造器或附属构造器
  def this(name: String, age: Int, gender: String) {
    this(name, age)
    this.gender = gender
  }
}

class Student(name: String, age: Int, val major: String) extends Person2(name, age) {
  println("this is the subclass of Person2,major is:" + major)

  override def toString = "Override toString..."

  override val school = "QH"
}

object Basic2 {

  def main(args: Array[String]) {

    var p = new Person //括号可省略
    p.name = "Jack"
    //输出 Jack,10
    println(p.name + "," + p.age)
    //        println(p.male)

    /*
    输出：
    this is the primary contructor
    Jacky,20,PDSU,null
     */
    val p2 = new Person2("Jacky", 20)
    println(p2.name + "," + p2.age + "," + p2.school + "," + p2.gender)

    /*
    输出：
    this is the primary contructor
    this is the subclass of Person2,major is:Math
    Override toString...
    QH
     */
    val stu = new Student("Justin", 20, "Math")
    println(stu.toString)
    println(stu.school)


  }

}

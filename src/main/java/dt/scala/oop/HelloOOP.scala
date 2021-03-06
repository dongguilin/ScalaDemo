package dt.scala.oop

/**
 * Created by hadoop on 2015/9/27.
 * Scala类的属性和对象私有字段
 */
object HelloOOP {

  def main(args: Array[String]): Unit = {
    val person = new Person()
    person.increment()
    person.increment()
    println(person.current)

    //        val student = new Student
    //        student.age = 10//没有age的set方法
    //        println(student.age)

    //    val student = new Student
    //    println(student.name)
  }

}

class Person {
  private var age = 0

  def increment() {
    age += 1
  }

  def current = age
}

class Student {
  private var privateAge = 0
  val name = "Scala"

  def age = privateAge

  def isYounger(other: Student) = privateAge < other.privateAge
}

package dt.scala.oop

/**
 * Created by hadoop on 2015/9/27.
 * Scala主构造器、私有构造器、构造器重载
 * Scala内部类
 */
object OOPInScala {

  def main(args: Array[String]) {
    val outer1 = new Outer("Spark")
    val outer2 = new Outer("Hadoop")
    val inner1 = new outer1.Inner("Scala")
    val inner2 = new outer2.Inner("Java")
    inner1.foo(inner1);
    inner2.foo(inner2);

    val p = new Teacher
    p.name = "Spark"

    p.sayHello

    //    	  val p2 = new Teacher2("Spark", 5)
    //    	  println(" : " + p2.age)

    val p2 = new Teacher2("Spark", 5, "male")
    println(" : " + p2.age)
  }
}

class Teacher {
  var name: String = _
  private var age = 27

  //private[this]  只能在该类内部访问
  private[this] val gender = "male"

  def this(name: String) {
    this
    this.name = name
  }

  def compare(teacher: Teacher): Unit = {
    //    this.gender.compareTo(teacher.gender)
  }

  def sayHello() {
    println(this.name + ":" + this.age + " : " + this.gender)
  }
}

//私有的主构造函数
class Teacher2 private(val name: String, val age: Int) {
  println("This is the primary constructor!!!")
  var gender: String = _
  println(gender)

  def this(name: String, age: Int, gender: String) {
    this(name, age)

    this.gender = gender
  }
}


class Outer(val name: String) {
  outer =>

  class Inner(val name: String) {
    def foo(b: Inner) = println("Outer: " + outer.name +
      " Inner: " + b.name)
  }

}

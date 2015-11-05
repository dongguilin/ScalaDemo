package dt.scala.type_parameterization

/**
 * Created by hadoop on 2015/11/4.
 * Scala中链式调用风格的实现代码
 */
class Animal {
  def breath: this.type = this

  def walk = {
    println("Animal walk")
    this
  }
}

class Cat extends Animal {

  var age = 0

  def setAge(age: Int) {
    this.age = age
    this
  }

  def eat: this.type = this

  def run = {
    println("running")
    this
  }
}

object Singleton_Types {

  def main(args: Array[String]) {
    val cat = new Cat
    cat.breath.eat.run.walk
    cat.setAge(11)
    val cat2 = new Cat()
    cat2.setAge(12)
    println(cat.age == cat2.age)
  }

}

package dt.scala.type_parameterization

/**
 * Created by hadoop on 2015/11/4.
 * Scala中链式调用风格的实现代码
 */
class Animal {
  def breath: this.type = this
}

class Cat extends Animal {
  def eat: this.type = this

  def run = {
    println("running")
  }
}

object Singleton_Types {

  def main(args: Array[String]) {
    val cat = new Cat
    cat.breath.eat.run
  }

}

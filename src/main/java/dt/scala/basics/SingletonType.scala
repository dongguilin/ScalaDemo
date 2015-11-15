package dt.scala.basics

/**
 * Created by hadoop on 2015/11/15.
 * 单例
 */
object SingletonType {

  def main(args: Array[String]) {

    import scala.reflect.runtime.universe._
    println(Scala.getClass) //class dt.scala.basics.Scala$
    println(typeOf[Scala.type]) //dt.scala.basics.Scala.type

    val java1 = new Java1
    val java2 = new Java1
    println(typeOf[java1.type]) //java1.type
    println(typeOf[java2.type]) //java2.type

    val content: java1.type = java1
    val jvm = new JVMLanguage
    println(jvm.method1)
    jvm.method1.method2
  }

}

object Scala

class Java1

class JVM {
  def method1: this.type = this
}

class JVMLanguage extends JVM {
  def method2: this.type = this
}


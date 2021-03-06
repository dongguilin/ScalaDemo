package dt.scala.oop

/**
 * Created by hadoop on 2015/9/27.
 * Scala中的继承：超类的构造、重写字段、重写方法
 */
object OverrideOps {

  def main(args: Array[String]) {
    val w = new Worker("Spark", 5, 100000)
    println("School :" + w.school)
    println("Salary :" + w.salary)
    println(w.toString())
  }

}

class Person1(val name: String, var age: Int) {
  println("The primary constructor of Person")

  val school = "BJU"

  def sleep = "8 hours"

  override def toString = "I am a Person1!"
}

class Worker(name: String, age: Int, val salary: Long) extends Person1(name, age) {
  println("This is the subClass of Person, Primary constructor of Worker")
  override val school = "Spark"

  override def toString = "I am a Worker!" + super.sleep


}

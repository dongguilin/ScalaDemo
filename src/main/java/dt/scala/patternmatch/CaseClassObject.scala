package dt.scala.patternmatch

/**
 * Created by hadoop on 2015/10/7.
 * Case class和Case object
 */
abstract class Person

case class Student(age: Int) extends Person

case class Worker(age: Int, salary: Double) extends Person

case object Shared extends Person

object CaseClassObject {
  def main(args: Array[String]) {
    def caseOps(person: Person) = person match {
      case Student(age) => println("I am " + age + " years old")
      case Worker(_, salary) => println("Wow, I got " + salary)
      case Shared => println("No property")
    }
    caseOps(Student(19))
    caseOps(Shared)

    //copy方法和带名参数
    val worker = Worker(29, 10000.1)
    val worker2 = worker.copy(salary = 19.95)
    val worker3 = worker.copy(age = 30)

    println(worker2.salary, worker2.age) //(19.95,29)
  }
}

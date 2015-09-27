package dt.scala.oop

/**
 * Created by hadoop on 2015/9/27.
 */
class AbstractClassOps {
  var id: Int = _
}

abstract class SuperTeacher(val name: String) {
  var id: Int
  var age: Int

  def teach
}

class TeacherForMaths(name: String) extends SuperTeacher(name) {
  override var id: Int = name.hashCode

  override def teach: Unit = {
    println("Teaching!")
  }

  //override def teach: Unit = ???//scala.NotImplementedError: an implementation is missing

  override var age: Int = 29
}

object AbstractClassOps {
  def main(args: Array[String]) {
    val teacher = new TeacherForMaths("Spark")
    teacher.teach

    println("teacher.id" + ":" + teacher.id)
    println(teacher.name + ":" + teacher.age)
  }
}

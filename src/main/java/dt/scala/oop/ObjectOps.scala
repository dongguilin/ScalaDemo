package dt.scala.oop

/**
 * Created by hadoop on 2015/9/27.
 * Scala单例对象、伴生对象
 */
object ObjectOps {

  def main(args: Array[String]) {
    println(University.newStudenNo) //1
    println(University.newStudenNo) //2
  }

}

class University {
  val id = University.newStudenNo
  private var number = 0

  def aClass(number: Int) {
    this.number += number
  }
}

object University {
  private var studentNo = 0

  def newStudenNo = {
    studentNo += 1
    studentNo
  }
}

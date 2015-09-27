package dt.scala.oop

/**
 * Created by hadoop on 2015/9/27.
 */
object ApplyOps {
  def main(args: Array[String]) {
    val array = Array(1, 2, 3, 4, 5)

    val a = new ApplyTest
    a.haveATry //Have a try on apply!

    //调用apply
    println(a()) //I am into Spark so much!!!  ()

    //调用伴生对象的apply
    val b = ApplyTest() //I am into Scala so much!!!
    b.haveATry //Have a try on apply!
  }
}

class ApplyTest {
  def apply() = println("I am into Spark so much!!!")

  def haveATry {
    println("Have a try on apply!")
  }
}

object ApplyTest {
  def apply() = {
    println("I am into Scala so much!!!")
    new ApplyTest
  }
}


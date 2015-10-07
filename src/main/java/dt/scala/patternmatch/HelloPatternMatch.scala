package dt.scala.patternmatch

/**
 * Created by hadoop on 2015/10/7.
 */
object HelloPatternMatch {

  def main(args: Array[String]) {
    val data = 2
    data match {
      case 1 => println("First")
      case 2 => println("Second")
      case _ => println("Not Known Number")
    }

    val result = data match {
      case i if i == 1 => "The First"
      case number if number == 2 => "The Second" + number
      case _ => "Not Known Number"
    }
    println(result)

    "Spark !" foreach { c => println(
      c match {
        case ' ' => "space"
        case ch => "Char: " + ch
      }
    )
    }

    "Hello" foreach {
      (c) => println(c)
    }


  }

}

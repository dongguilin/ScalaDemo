package dt.scala.patternmatch


/**
 * Created by hadoop on 2015/10/7.
 * Option使用
 */
object OptionInternal {

  def main(args: Array[String]) {
    val scores = Map("Alice" -> 99, "Spark" -> 100)

    scores.get("Alice") match {
      case Some(score) => println(score)
      case None => println("No score")
    }
  }
}

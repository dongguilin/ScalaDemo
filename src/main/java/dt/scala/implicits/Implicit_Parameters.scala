package dt.scala.implicits

/**
 * Created by hadoop on 2015/11/9.
 * Scala隐式参数
 */
object Context_Implicits {
  implicit val default: String = "Flink"
}

object Param {
  def print(context: String)(implicit language: String) = println(language + ":" + context)
}

object Implicit_Parameters {
  def main(args: Array[String]) {
    Param.print("Spark")("Scala")

    import Context_Implicits.default
    Param.print("Hadoop")

  }

}

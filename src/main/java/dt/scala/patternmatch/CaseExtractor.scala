package dt.scala.patternmatch

/**
 * Created by hadoop on 2015/11/15.
 * 模式匹配下的提取器
 */
object CaseExtractor {

  def main(args: Array[String]) {

    (1 to 9) toList match {
      case _ :> 9 => println("Hadoop")
    }
    (1 to 9).toList match {
      case x :> 8 :> 9 => println("Spark")
    }
    (1 to 9).toList match {
      case :>(:>(_, 8), 9) => println("Flink")
    }
  }

}

object :> {
  def unapply[A](list: List[A]) = {
    Some(list.init, list.last)
  }

}

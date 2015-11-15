package dt.scala.patternmatch

/**
 * Created by hadoop on 2015/11/15.
 * 模式匹配下的for循环
 */
object CaseFor {

  def main(args: Array[String]) {

    for (i <- List(22, 33, 44)) println(i)

    for (index@"Flink" <- Set("Hadoop", "Flink", "Spark")) println(index)

    for ((language, "Hadoop") <- Set(("Scala" -> "Spark"), ("Java" -> "Hadoop"))) println(language)

    for ((k, v: Int) <- List(("Spark" -> 5), ("Hadoop" -> "HDFS"))) println((k, v))

  }

}

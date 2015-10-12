package dt.scala.patternmatch

/**
 * Created by hadoop on 2015/10/7.
 * Type、Array、List、Tuple模式匹配
 * 变量声明中的模式、for表达式中的模式、样例类
 */
object PatternMatchMore {

  def main(args: Array[String]) {

    def match_type(t: Any) = t match {
      case p: Int => println("It is Integer")
      case p: String => println("It is String, the content is : " + p)
      case m: Map[_, _] => m.foreach(println)
      case _ => println("Unknown type!!!")
    }

    match_type(2)
    match_type("Spark")
    match_type(Map("Scala" -> "Spark", "Hello" -> "World"))

    def match_array(arr: Any) = arr match {
      //      case Array(0) => println("Array:" + "0")
      case Array(x, y) => println("Array:" + x + " " + y)
      case Array(0, _*) => println("Array:" + "0 ...")
      case _ => println("something else")
    }

    match_array(Array(0))
    match_array(Array(0, 1))
    match_array(Array(0, 1, 2, 3, 4, 5))


    def match_list(lst: Any) = lst match {
      case 0 :: Nil => println("List:" + "0")
      case x :: y :: Nil => println("List:" + x + " " + y)
      case 0 :: tail => println("List:" + "0 ...")
      case _ => println("something else")
    }

    match_list(List(0))
    match_list(List(3, 4))
    match_list(List(0, 1, 2, 3, 4, 5)) //List:0 ...


    def match_tuple(tuple: Any) = tuple match {
      case (0, _) => println("Tuple:" + "0")
      case (x, 0) => println("Tuple:" + x)
      case _ => println("something else")
    }

    match_tuple((0, "Scala"))
    match_tuple((2, 0))
    match_tuple((0, 1, 2, 3, 4, 5))

    println("-----------------------")

    //变量声明中的模式(/%方法返回包含商和余数的对偶)
    val (a, b) = BigInt(10) /% 3
    println((a, b)) //(3,1)

    //for表达式中的模式(在for推导式中，失败的匹配将被安静的忽略)
    import scala.collection.JavaConversions.propertiesAsScalaMap
    //将Java的Properties转换成Scala映射
    //    for ((k, v) <- System.getProperties()) println(k + "->" + v)
    for ((k, "") <- System.getProperties) println(k)
    //使用守卫
    for ((k, v) <- System.getProperties if v == "") println(k)


    //样例类
    abstract class Amount
    case class Dollar(value: Double) extends Amount
    case class Currency(value: Double, unit: String) extends Amount
    case object Nothing extends Amount //样例对象

    Dollar(11) match {
      case Dollar(v) => "$" + v
      case Currency(_, u) => "Oh noes,I got " + u
      case Nothing => ""
    }


  }

}

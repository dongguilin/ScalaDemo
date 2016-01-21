package dt.scala.forexpression

/**
 * Created by hadoop on 2016/1/21.
 * for表达式
 * 生成器、定义、过滤
 */
object For_Expressive {

  def main(args: Array[String]) {
    val tom = Person("Tom", true)
    val lili = Person("lili", false)
    val jack = Person("Jack", true, tom, lili)
    val persons = List(tom, lili, jack)

    val result = persons.filter(p => p.isMale) flatMap (p => p.children map (child => (p.name, child.name)))
    println(persons.filter(p => p.isMale))
    println(result)

    //<-为生成器；name=p.name是定义；if p.isMale 是过滤
    val result2 = for (p <- persons; name = p.name; if p.isMale; child <- p.children)
      yield (name, child.name)
    println(result2)

    val content = for {
      x <- List(1, 2, 3)
      y <- List("Hadoop", "Spark", "Flink")
    } yield (x, y)
    println(content)
  }

}

case class Person(name: String, isMale: Boolean, children: Person*)

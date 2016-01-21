package dt.scala.forexpression


/**
 * Created by hadoop on 2016/1/21.
 * 使用for表达式作查询
 */
case class Book(title: String, authors: List[String])

object For_Query {

  def main(args: Array[String]) {
    val books: List[Book] = List(
      Book("Structure and Interpretation ", List("Abelson , Harold", "Sussman")),
      Book("Principles of Compiler Design", List("Aho, Alfred", "Ullman, Jeffrey")),
      Book("Programming in Modula-2", List("Wirth, Niklaus")),
      Book("Introduction to Functional Programming", List("Bird, Richard")),
      Book("The Java Language Specification", List("Gosling, James", "Joy, Bill", "Steele, Guy", "Bracha, Gilad")))

    val result1 = for (book <- books if book.title.indexOf("Programming") >= 0) yield book.title
    println(result1)

    val result2 = for (book <- books; author <- book.authors; if author.startsWith("Joy")) yield book.title
    println(result2)
  }

}

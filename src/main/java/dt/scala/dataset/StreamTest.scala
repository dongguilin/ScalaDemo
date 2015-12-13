package dt.scala.dataset

import scala.io.Source

/**
 * Created by hadoop on 2015/12/13.
 * 流 #::
 */
object StreamTest {

  //#::操作符构建出流
  def numsFrom(n: BigInt): Stream[BigInt] = n #:: numsFrom(n + 1)

  def main(args: Array[String]) {
    val tenOrMore = numsFrom(10)
    println(tenOrMore) //Stream(10, ?)
    //使用tail强制对下一个元素未值
    println(tenOrMore.tail.tail.tail) //Stream(13, ?)

    //流的方法是懒执行的
    val squares = numsFrom(2).map(x => x * x)
    println(squares) //Stream(4, ?)
    println(squares.tail) //Stream(9, ?)
    //如果想得到多个答案，调用take，然后用force，这将强制对所有值求值
    println(squares.take(5).force) //Stream(4, 9, 16, 25, 36)

    /*
    abc
    呵呵
    abc
    hello aa word hehe
    abc
     */
    //从迭代器构造一个流，流将缓存访问过的行，允许重新访问
    val lines = Source.fromFile("E:/Test/test1.txt").getLines().toStream
    println(lines) //Stream(abc, ?)
    println(lines(3)) //hello aa word hehe
    println(lines) //Stream(abc, 呵呵, abc, hello aa word hehe, ?)
    println(lines.toVector) //Vector(abc, 呵呵, abc, hello aa word hehe, abc)
    println(lines.take(4).force) //Stream(abc, 呵呵, abc, hello aa word hehe)
    println(lines.take(4).force.toList) //List(abc, 呵呵, abc, hello aa word hehe)

    //TODO ?怎么用
    //    println(lines.takeWhile(p => {
    //      println(p)
    //      p.startsWith("hello")
    //    }))

    println(numsFrom(2).takeWhile(_ <= 5).mkString(",")) //2,3,4,5


  }

}

package dt.scala.dataset

/**
 * Created by hadoop on 2015/12/12.
 * 并行集合
 */
object ParTest {

  def main(args: Array[String]) {

    var start = System.currentTimeMillis()
    //    for (i <- (0 until 100000).par) print(i + " ")
    println((0 to 100).par)
    println((0 until 100000).par.sum)
    printf("\n耗时：%d\n", (System.currentTimeMillis() - start))

    start = System.currentTimeMillis()
    //    for (i <- (0 until 100000)) print(i + " ")
    println((0 until 100000).sum)
    printf("\n耗时：%d\n", (System.currentTimeMillis() - start))

  }

}

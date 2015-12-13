package dt.scala.dataset

/**
 * Created by hadoop on 2015/12/13.
 * 折叠 foldLeft(/:)、foldRight(:\)
 *
 * 使用二元函数组合集合中的元素
 */
object FoldLeftRightTest {

  def main(args: Array[String]) {

    //0-1-7-2-9=-19
    println(List(1, 7, 2, 9).foldLeft(0)(_ - _)) //-19
    //或者使用/:
    println((0 /: List(1, 7, 2, 9))(_ - _)) //-19

    println(List(1, 7, 2, 9).foldLeft("0")(_ + _)) //01729

    println((List(1, 7, 2, 9) :\ 0)(_ + _)) //19

    //计算每个字符出现的次数
    val freq = scala.collection.mutable.Map[Char, Int]()
    for (c <- "Mississippi") freq(c) = freq.getOrElse(c, 0) + 1
    println(freq) //Map(M -> 1, s -> 4, p -> 2, i -> 4)

    //使用/:
    //左操作元是一个部分填充的映射，右操作元是新字母，结果是扩编后的映射，该映射又被作为下一个op调用的输入，最后的结果是包含所有计数的映射
    val m1 = (Map[Char, Int]() /: "Mississippi") { (m, c) => m + (c -> (m.getOrElse(c, 0) + 1)) }
    println(m1) //Map(M -> 1, i -> 4, s -> 4, p -> 2)

    //任何while循环都可以用折叠来替代
    var num = 10
    while (num > 0) {
      print(num + ",")
      num -= 1
    }
    println

    (1 to 10).foldRight(0) { (a, b) => {
      print(a + ",")
      a
    }
    }


  }

}

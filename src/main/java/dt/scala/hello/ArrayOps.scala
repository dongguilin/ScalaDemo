package dt.scala.hello

import java.util

import scala.collection.mutable.ArrayBuffer

/**
 * Created by hadoop on 2015/9/26.
 * Scala数组操作
 */
object ArrayOps {

  def main(args: Array[String]): Unit = {

    val nums = new Array[Int](10)
    val a = new Array[String](10)
    val s = Array("Hello", "World")
    s(0) = "Goodbye"

    println(nums(1) + " " + nums.length) //0 10
    //    println(nums(12))//ArrayIndexOutOfBoundsException
    println(a(5) + " " + nums.length) //null 10
    println(s(0)) //Goodbye
    for (i <- 0 until a.length)
      println(i + ": " + a(i))
    //////////////////////////////////////////


    val b = ArrayBuffer[Int]()
    b += 1 //1
    b +=(1, 2, 3, 5) //1,1,2,3,5
    b ++= Array(8, 13, 21) //1,1,2,3,5,8,13,21
    b.trimEnd(5) //1,1,2
    b.insert(2, 6) //1,1,6,2
    b.insert(2, 7, 8, 9) //1,1,7,8,9,6,2
    b.remove(2) //1,1,8,9,6,2
    b.remove(2, 3) //1,1,2
    b.toArray //1,1,2
    //////////////////////////////////////////


    /*
        yield
       1.针对每一次 for 循环的迭代, yield 会产生一个值，被循环记录下来 (内部实现上，像是一个缓冲区).
       2.当循环结束后, 会返回所有 yield 的值组成的集合.
       3.返回集合的类型与被遍历的集合类型是一致的.
     */
    val c = Array(2, 3, 5, 7, 11)
    val result = for (elem <- c) yield 2 * elem //4,6,10,14,22
    //for循环加守卫(guards)
    for (elem <- c if elem % 2 == 0) yield 2 * elem //4
    //这种方式更常用
    c.filter(_ % 2 == 0).map(2 * _) //4
    ///////////////////////////////////////////


    Array(1, 7, 2, 9).sum //19
    val maxStr = ArrayBuffer("Mary", "had", "a", "little", "lamb").max
    println(maxStr) //little

    val d = ArrayBuffer(1, 7, 2, 9)
    val bSorted = d.sorted
    println(bSorted) //ArrayBuffer(1, 2, 7, 9)

    val e = Array(1, 7, 2, 9)
    scala.util.Sorting.quickSort(e)

    println(e.mkString(" and ")) //1 and 2 and 7 and 9
    println(e.mkString("<", ",", ">")) //<1,2,7,9>

    //矩阵
    val matrix = Array.ofDim[Double](3, 4)
    println(matrix.length) //3
    matrix(2)(1) = 42
    //    println(matrix(4))//ArrayIndexOutOfBoundsException
    for (elem <- matrix; e <- elem) println(e)
    for (i <- 0 until 3) {
      println
      for (j <- 0 until 4) {
        printf("(%d,%d)=%f\t", i, j, matrix(i)(j))
      }
    }

    val triangle = new Array[Array[Int]](10)
    for (i <- 0 until triangle.length)
      triangle(i) = new Array[Int](i + 1)

  }

}

package dt.scala.dataset

/**
 * Created by hadoop on 2015/12/13.
 * 扫描 scanLeft、scanRight
 * 使用二元函数组合集合中的元素
 */
object ScanLeftRight {

  def main(args: Array[String]) {

    val a1 = (0 to 10) reduceLeft (_ + _)
    val a2 = (0 to 10).foldLeft(0)(_ + _)
    println(a1) //55
    println(a2) //55

    //scanLeft方法将折叠和映射操作结合在一起，得到的是包含所有中间结果的集合
    val a3 = (0 to 10).scanLeft(0)(_ + _)
    println(a3) //Vector(0, 0, 1, 3, 6, 10, 15, 21, 28, 36, 45, 55)

    //...8+(9+(10+0))
    println((0 to 10).scanRight(0)(_ + _))

  }

}

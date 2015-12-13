package dt.scala.dataset

/**
 * Created by hadoop on 2015/12/13.
 * 懒视图
 * 懒集合对于处理那种需要以多种方式进行变换的大型集合是很有好处的，因为它避免了构建出大型中间集合的需要
 */
object ViewTest {

  def main(args: Array[String]) {

    val powers = (0 until 1000).view.map(scala.math.pow(10, _))
    println(powers(10)) //1.0E10

    val v1 = (0 to 10).view.map(_ + 1)
    println(v1) //SeqViewM(...)
    println(v1.force) //Vector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)

    //计算出10的n次方的集合，然后再对每一个得到的值取倒数
    (0 to 10).map(scala.math.pow(10, _)).map(1 / _)
    //记住两个map操作的视图，当求值动作被强制执行时，对于每一个元素，这两个操作被同时执行，不需要额外构建中间集合
    (0 to 10).view.map(scala.math.pow(10, _)).map(1 / _).force

    (0 to 10).map(x => scala.math.pow(10, -x))
  }

}

package dt.scala.dataset

/**
 * Created by hadoop on 2015/12/13.
 * 化简 reduceLeft、reduceRight
 * 使用二元函数组合集合中的元素
 */
object ReduceLeftRightTest {

  def main(args: Array[String]) {
    //((1-7)-2)-9=1-7-2-9=-17
    println(List(1, 7, 2, 9) reduceLeft (_ - _)) //-17
    //1-(7-(2-9))=1-7+2-9=-13
    println(List(1, 7, 2, 9) reduceRight (_ - _)) //-13

  }

}

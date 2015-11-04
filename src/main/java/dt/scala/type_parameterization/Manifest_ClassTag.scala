package dt.scala.type_parameterization

import scala.reflect.ClassTag

/**
 * Created by hadoop on 2015/10/17.
 * ClassTag、Manifest、ClassManifest、TypeTag
 * Mainfest和ClassMainfest在某些情况下有些问题，建议使用ClassTag、TypeTag
 */
object Manifest_ClassTag {

  def main(args: Array[String]) {

    //1  Manifest推荐使用ClassTag或TypeTag替代
    //Manifest运行时会获取类型信息，并把类型信息赋给Array
    def arrayMake[T: Manifest](first: T, second: T) = {
      val r = new Array[T](2)
      r(0) = first
      r(1) = second
      r
    }
    arrayMake(1, 2) foreach println _

    //2  写法不常用
    //隐式转换
    def mainf[T](x: List[T])(implicit m: Manifest[T]) = {
      if (m <:< manifest[String]) println("List strings")
      else println("Some other types")
    }
    mainf(List("Hadoop", "Spark")) //List strings
    mainf(List(3, 4)) //Some other types
    mainf(List("Hello", 1)) //Some other types

    //error
    //    def arrayMake2[T](first:T,second:T)={
    //      val r = new Array[T](2)
    //      r(0) = first
    //      r(1) = second
    //      r
    //    }
    //    arrayMake(1,2) foreach println _

    //常用ClassTag
    def makeArray[T: ClassTag](elems: T*) = Array[T](elems: _*)
    makeArray("Hello", "World", "!") foreach println _
    makeArray("3", 3) foreach println _

    class A[T]

    val m = manifest[A[String]]
    println(m)
    val cm = classManifest[A[String]]
    println(cm)

  }

}

package dt.scala.type_parameterization

/**
 * Created by hadoop on 2015/11/8.
 * Scala中Self Types
 */
class Self {
  self =>
  val temp = "Scala"

  def foo = self.temp + this.temp
}

trait S1

class S2 {
  this: S1 =>
}

class S3 extends S2 with S1

trait T {
  this: S1 =>
}

object S4 extends T with S1

object Self_Types {

  class Outer {
    outer =>
    val v1 = "Spark"

    class Inner {
      println(outer.v1)
    }

  }

  val c = new S2 with S1

}

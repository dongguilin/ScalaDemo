package dt.scala.type_parameterization

/**
 * Created by hadoop on 2015/11/8.
 * Scala中Dependency Injection
 */
trait Logger {
  def log(msg: String)
}

trait Auth {
  auth: Logger =>
  def act(msg: String): Unit = {
    auth.log(msg)
  }
}

object DI extends Auth with Logger {
  override def log(msg: String) = println(msg)
}

object Dependency_Injection {
  def main(args: Array[String]) {
    DI.log("I hope you will like it")
  }
}

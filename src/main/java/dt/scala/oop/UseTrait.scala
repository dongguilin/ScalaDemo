package dt.scala.oop

/**
 * Created by hadoop on 2015/9/27.
 *
 * Scala中作为接口的trait、在对象中混入trait代码
 * Scala多重继承、多重继承构造器执行顺序及AOP实现
 */
object UseTrait extends App {

  val t1 = new PianoTeacher
  t1.playPiano
  t1.teach
  println("--------------------")

  val t2 = new Human with TTeacher with PianoPlayer {
    def teach = {
      println("I'm teaching students.")
    }
  }
  t2.playPiano
  t2 teach

  new ConcreteLogger().log("hello")
  println("--------------------------------")


  val work = new Work with TBeforeAfter
  work.doAction

  println("----------------------------------")


  //  val logger = new ConcreteLogger
  //  logger.concreteLog

  //在对象中混入trait代码，ConcreteLogger调用TraitLogger的log实现
  val logger = new ConcreteLogger with TraitLogger
  logger.concreteLog
  logger.log("hehe")


  //  	val logger = new Test
  //  	logger.test;

  //    val account = new MyAccount with TraitLoggered
  //    account.save

}

trait Logger {
  //    def log(msg: String)

  def log(msg: String) {}
}

class ConcreteLogger extends Logger with Cloneable {

  //  override def log(msg: String) = println("Log: " + msg)

  def concreteLog {
    log("It's me !!!")
  }
}

trait TraitLogger extends Logger {
  override def log(msg: String) {
    println(" TraitLogger Log content is : " + msg)
  }
}

trait TraitLoggered {
  def loged(msg: String) {
    println("TraitLoggered Log content is : " + msg)
  }
}

trait ConsoleLogger extends TraitLogger {
  override def log(msg: String) {
    println("Log from Console :" + msg)
  }
}

class Test extends ConsoleLogger {
  def test {
    log("Here is Spark!!!")
  }

}

abstract class Account {
  def save
}

class MyAccount extends Account with ConsoleLogger {
  def save {
    log("11")
  }
}

class Human {
  println("Human")
}

trait TTeacher extends Human {
  println("TTeacher")

  def teach
}

trait PianoPlayer extends Human {
  println("PianoPlayer")

  def playPiano = {
    println("I’m playing piano. ")
  }
}

class PianoTeacher extends Human with TTeacher with PianoPlayer {
  override def teach = {
    println("I’m training students. ")
  }
}

//AOP
trait Action {
  def doAction
}

trait TBeforeAfter extends Action {
  abstract override def doAction {
    println("Initialization")
    super.doAction
    println("Destroyed")
  }
}

class Work extends Action {
  override def doAction = println("Working...")
}

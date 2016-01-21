package dt.scala.actor.scala

import scala.actors.Actor

/**
 * Created by hadoop on 2016/1/19.
 * Scala并发编程初体验
 *
 * Spark中的Master和Worker继承自akka的Actor
 *
 */
object HelloActor {

  def main(args: Array[String]) {
    FirstActor.start()
    SecondActor.start()
  }

}

object FirstActor extends Actor {
  override def act(): Unit = {
    println(Thread.currentThread().getName)
    for (i <- 1 to 10) {
      println("FirstActor step:" + i)
      Thread.sleep(1000)
    }
  }
}

object SecondActor extends Actor {
  override def act(): Unit = {
    println(Thread.currentThread().getName)
    for (i <- 1 to 10) {
      println("SecondActor step:" + i)
      Thread.sleep(1000)
    }
  }
}

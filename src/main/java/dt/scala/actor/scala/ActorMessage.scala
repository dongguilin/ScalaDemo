package dt.scala.actor.scala

import scala.actors.Actor
import scala.actors.Actor._


/**
 * Created by hadoop on 2016/1/18.
 * Scala并发编程匿名Actor、消息传递、偏函数
 *
 * Spark源码体现
 * Master中 case RegisterWorker(id, workerHost, workerPort, cores, memory, workerUiPort, publicAddress) =>
 * Worker中 master ! RegisterWorker
 */
object ActorMessage extends Actor {
  override def act(): Unit = {
    //    println(Thread.currentThread().getName)
    //    for (i <- 1 to 10) {
    //      println("Step:" + i)
    //      Thread.sleep(2000)
    //    }
    while (true) {
      receive {
        case msg => println("Message content Actor from inbox: " + msg)
      }
    }
  }
}

object ActorMessages {
  def main(args: Array[String]) {

    //匿名Actor
    val actor_Message = actor {
      while (true) {
        receive {
          case msg => println("Message content Actor from inbox: " + msg)
        }
      }
    }

    val double_Message = actor {
      while (true) {
        //receive是一个偏函数
        receive {
          case msg: Double => println("Double number from inbox: " + msg)
        }
      }
    }

    ActorMessage.start()
    ActorMessage ! "Hello"

    actor_Message ! "Spark"
    double_Message ! Math.PI
    double_Message ! "Hadoop"

  }
}

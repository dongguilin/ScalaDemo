package dt.scala.actor.akka

import akka.actor.Actor


/**
 * Created by hadoop on 2016/1/17.
 */
class Greeter extends Actor {

  override def receive: Receive = {
    case Greeter.Greet =>
      println("Hello World!")
      sender() ! Greeter.Done
  }

}

object Greeter {

  case object Greet

  case object Done

}

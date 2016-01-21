package dt.scala.actor.scala

import java.net.{InetAddress, UnknownHostException}

import scala.actors.Actor

/**
 * Created by hadoop on 2016/1/20.
 * Scala并发编程React、loop
 */
object Actor_More_Effective {
  def main(args: Array[String]) {
    NameResolver.start()
    NameResolver ! Net("www.baidu.com", Actor.self)

    NameResolver ! "hello"
    NameResolver ! "EXIT"
    Thread.sleep(2000)
    NameResolver ! "world"

    println(Actor.self.receiveWithin(1000) { case x => x })
  }

}

case class Net(name: String, act: Actor)

object NameResolver extends Actor {
  override def act(): Unit = {
    //    Actor.react{
    //      case Net(name, actor)=>
    //        actor ! getIp(name)
    //        act
    //      case "EXIT" => println("Name resolver exiting!")
    //      case msg =>
    //        println("Unhandled message:"+msg)
    //        act
    //    }

    //无限循环
    loop {
      Actor.react {
        case Net(name, actor) =>
          actor ! getIp(name)
        case msg =>
          println("Unhandled message:" + msg)
      }
    }
  }

  def getIp(name: String): Option[InetAddress] = {
    try {
      println(InetAddress.getByName(name))
      Some(InetAddress.getByName(name))
    } catch {
      case e: UnknownHostException => None
    }

  }

}

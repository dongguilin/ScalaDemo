package dt.scala.actor.scala

import scala.actors.Actor

/**
 * Created by hadoop on 2016/1/20.
 * Scala并发编程原生Actor、Case class下的消息传递和偏函数
 *
 *
 */
object Actor_With_CaseClass {
  def main(args: Array[String]) {
    val hiActor = new PrintActor
    hiActor.start()
    hiActor ! Person("张三", 22)

    //    Actor.self.receive{
    //      case msg =>println(msg)
    //    }

    Actor.self.receiveWithin(1000) {
      case msg => println(msg) //调试时receiveWithin可能会出现不超时的情况  ？
    }
  }

}

case class Person(name: String, age: Int)

class PrintActor extends Actor {
  override def act(): Unit = {
    //    while(true){
    receive {
      case Person(name, age) => {
        println("name:" + name + " ,age:" + age)
        Thread.sleep(2000)
        sender ! "Echo!!!"
      }
      case _ => println("Something else...")
    }
    //    }
  }
}

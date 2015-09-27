package com.guilin.scala.obj

/**
 * Created by hadoop on 2015/8/16.
 */
object TrafficLightColor extends Enumeration {

  type TrafficLightColor = Value
  val Red, Yellow, Green = Value

  val Red2 = Value(4, "Stop")
  val Yellow2 = Value(10)
  //名称为"Yellow2"
  val Green2 = Value("Go") //ID为11

  def doWhat(color: TrafficLightColor) = {
    if (color == Red || color == Red2) "stop"
    else if (color == Yellow || color == Yellow2) "hurry up"
    else "go"
  }

  def main(args: Array[String]) {
    println(doWhat(Red2)) //stop
    println(Red.id) //0
    for (c <- TrafficLightColor.values)
      println(c.id + ": " + c)

    println(TrafficLightColor(1))
    println(TrafficLightColor.withName("Stop"))
  }

}

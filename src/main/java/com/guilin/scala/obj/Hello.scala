package com.guilin.scala.obj

/**
 * Created by hadoop on 2015/8/16.
 * 扩展App特质
 */
object Hello extends App {
  if (args.length > 0) {
    println("Hello, " + args(0))
  } else {
    println("Hello,World!")
  }

}

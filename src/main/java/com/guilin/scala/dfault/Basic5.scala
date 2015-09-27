package com.guilin.scala.dfault

/**
 * Created by hadoop on 2014/10/31.
 */
class Basic5 {

}


case class Book(name: String, author: String)

object Basic5 extends App {
  val value = 1
  val result = value match {
    case 1 => "one"
    case 2 => "two"
    case _ => "some other number"
  }

  val result2 = value match {
    case i if i == 1 => "one"
    case i if i == 2 => "two"
    case _ => "some other number"
  }
  println("result of match is:" + result)
  println("result of match is:" + result2)

  def t(obj: Any) = obj match {
    case x: Int => println("Int")
    case s: String => println("String")
    case _ => println("unknown type")
  }

  t(1L)

  val macTalk = Book("MacTalk", "C30")
  macTalk match {
    case Book(name, author) => println("this is book")
    case _ => println("unknown")
  }

  val list = List(1, 2, 3)

}
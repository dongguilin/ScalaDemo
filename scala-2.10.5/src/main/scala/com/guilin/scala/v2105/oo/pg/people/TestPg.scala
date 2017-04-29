package com.guilin.scala.v2105.oo.pg.people

import org.scalatest.FunSuite

/**
  * Created by Administrator on 2017/4/29.
  */
class TestPg extends FunSuite {

  test("people.Person.description") {
    val p = new Person
    println(p.description)
    println(p.description2)
  }

}

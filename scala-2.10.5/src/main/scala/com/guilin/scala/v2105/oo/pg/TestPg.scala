package com.guilin.scala.v2105.oo.pg

import com.guilin.scala.v2105.oo.pg.people.Person
import org.scalatest.FunSuite

/**
  * Created by Administrator on 2017/4/29.
  */
class TestPg extends FunSuite {

  test("people.Person.description2") {
    val p = new Person
    //println(p.description) //wrong
    println(p.description2)
  }

}

package com.guilin.scala.v2105.oo.pg.people

/**
  * Created by Administrator on 2017/4/29.
  */
class Person {
  var name = defaultName //从包对象拿到的常量
  private[people] def description = "A person with name " + name

  private[pg] def description2 = "pg description2" + name
}

package com.guilin.scala.v2105.oo.pg

/**
  * Created by Administrator on 2017/4/29.
  * 把工具函数或常量添加到包而不是某个Utils对象，这是更加合理的做法
  * 包可以包含类、对象和特质，但不能包含函数或变量的定义，这是Java虚拟机的局限
  */
package object people {
  val defaultName = "John Q. Public"
}

package com.guilin.scala.v2105.file

import java.io.File
import java.net.URL

import org.scalatest.FunSuite

import scala.sys.process._

/**
  * Created by Administrator on 2017/4/29.
  * 进程控制
  * scala.sys.process包提供了用于与shell程序交互的工具
  */
class TestProcess extends FunSuite {

  ignore("进程控制") {
    //显示上层目录的所有文件，执行结果被打印到标准输出
    "ls -al .." !
    //    val result = "ls -al .." !!
    //      "ls -al .." #| "grep sec" !

    "ls -al .." #> new File("output.txt")
    "ls -al .." #>> new File("output.txt")
    "grep sec" #< new File("output.txt")
    "grep Scala" #< new URL("http://horstmann.com/index.html")
  }

}

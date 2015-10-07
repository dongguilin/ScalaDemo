package dt.scala.oop

/**
 * Created by hadoop on 2015/10/6.
 * Scala中包的定义、包对象、包的引用、包的引式引用
 */

package com.scala.spark

  //包对象（在包中定义包对象，包中所有类就可以直接访问包对象的成员和方法）
  package object people {
    val defaultName = "Scala"
  }

  package people {
    class people {
      var name = defaultName
    }
  }

  //Scala包的隐式引用
  // import java.lang._
  // import scala._
  // import Predef._

  import java.awt.{Color, Font}//引用包下部分类
  import java.util.{HashMap => JavaHashMap}//将HashMap命令为JavaHashMap别名
  import scala.{StringBuilder => _}//不引入scala.StringBuilder


  class PackageOps {}


  package spark.navigation {

    abstract class Navigator {
      def act
    }

    //测试包
    package tests {
      // 在spark.navigation.tests包里
      class NavigatorSuite {
      }
    }

    //具体实现包
    package impls {
      class Action extends Navigator {
        def act = println("Action")
      }
    }

  }

  package hadoop {

    package navigation {
      class Navigator

    }

    package launch {
      class Booster {
        val nav = new navigation.Navigator//包的引用

      }
    }

  }

object PackageOps {

}

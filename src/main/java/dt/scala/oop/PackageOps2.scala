package dt.scala.oop

/**
 * Created by hadoop on 2015/10/7.
 * 作用域规则
 * 串联式包语句
 * 包对象
 */
object PackageOps2 {

}

package com{
  package horstmann{
    object Utils{
      def percentOf(value:Double,rate:Double)=value*rate/100

      //使用绝对包名，以_root_开始，可避免引用冲突
      val subordinates= new _root_.scala.collection.mutable.ArrayBuffer[Double]
    }

    package impatient{
      class Employee{
        var salary = 30
        def giveRaise(rate:Double): Unit ={
          Utils.percentOf(salary,rate)//可访问上层包horstmann中的对象
        }
      }

      package subp{
        class Employee{
          println(Utils.percentOf(100,3))
        }
      }

    }
  }

  //串联式包语句
  package com.horstmann.impatient{
    object test{
      //com和com.horstmann的成员在这里不可见
//      println(Utils.subordinates)
    }
  }

  //包对象
  package com.horstmann.impatient{

    //包可以包含类、对象和特质，但不能包含函数或变量的定义，这是java虚拟机的局限
    //包对象被编译成带有静态方法和字段的JVM类
    class Student
    object Student
    trait run
//    val a=1
//    def a

    //把工具函数或常量添加到包对象而不是某个Utils对象，这是更加合理的做法
    package object people{
      val defaultName = "John"
      def add(a:Int, b:Int) = a + b
    }

    package people{
      class Person{
        var name = defaultName//从包对象拿到的常量
      }
    }
  }


  //包可见性
  package com.horstmann.impatient{
    class PersonTest{
      private[horstmann] def description = "A person"
    }
  }
  package com.horstmann{

  import dt.scala.oop.com.com.horstmann.impatient.PersonTest

  object test{
      new PersonTest().description
    }
  }

}

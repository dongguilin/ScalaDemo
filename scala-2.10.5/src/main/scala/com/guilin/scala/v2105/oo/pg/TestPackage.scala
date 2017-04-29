package com.guilin.scala.v2105.oo

/**
  * Created by Administrator on 2017/4/29.
  */
class TestPackage {

}

package com {
  package horstmann {

    object Utils {
      def percentOf(value: Double, rate: Double) = value * rate / 100
    }

    package collection {

    }

    package impatient {

      class Employee {
        var salary: Double = _

        def giveRaise(rate: scala.Double): Unit = {
          salary += Utils.percentOf(salary, rate)
        }
      }

      class Manager {
        //new collection.mutable.ArrayBuffer[Employee]()//wrong
        //scala中包名是相对的，包引入冲突问题，可以使用绝对包名或串联式包语句解决
        val subodinates = new _root_.scala.collection.mutable.ArrayBuffer[Employee]() //使用绝对包名
      }

    }

  }

}

//串联式包语句
package com.horstmann.impatient {
  //com和com.horstmann的成员在这里不可见
  package people {

    class Person {
      val subodinates = new collection.mutable.ArrayBuffer[Employee]()
    }

  }

}
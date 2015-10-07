package dt.scala.oop

/**
 * Created by hadoop on 2015/10/6.
 * Scala中包、类、对象、成员、伴生类、伴生对象访问权限
 */

package spark {

  package navigation {

    //将访问权限范围扩展到spark，spark包下的所有类和对象，包括子包中的所有类和对象等都可访问Navigator
//    private[spark] class Navigator {
    class Navigator {
      protected[navigation] def useStarChart() {}
      class LegOfJourney {
        private[Navigator] val distance = 100
      }

      //仅当前实例对象可见
      private[this] var speed = 200
    }


    package test{

      object TestUseStartChart{
        println(new Navigator().useStarChart())
      }

    }

  }

  package launch {

    import navigation._

    object Vehicle {
      //将guide的访问权限扩展到launch包，launch包及其子包下的所有类和对象都可访问guide
      private[launch] val guide = new Navigator
    }


  }

}

  class PackageOpsAdvanced {

  import PackageOpsAdvanced.power

  private def canMakeItTrue = power > 10001

}

object PackageOpsAdvanced {
  private def power = 10000

  def makeItTrue(p: PackageOpsAdvanced): Boolean = {
    val result = p.canMakeItTrue
    result
  }
}

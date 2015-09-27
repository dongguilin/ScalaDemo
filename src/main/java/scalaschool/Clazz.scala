package scalaschool

/**
 * Created by guilin1 on 15/8/1.
 * 基础
 * 1.类
 * 2.构造函数
 * 3.函数VS方法
 * 4.继承
 * 5.重载方法
 * 6.抽象类
 * 7.特质
 * 8.with关键字
 * 9.类型
 */
object Clazz {


  def main(args: Array[String]) {


    //类
    class Calculator {
      val brand: String = "HP"

      def add(m: Int, n: Int): Int = m + n
    }
    val calc = new Calculator
    println(calc.brand)
    println(calc.add(2, 3))


    //构造函数
    class Calculator2(brand: String) {
      /**
       * A constructor.
       */
      val color: String = if (brand == "TI") {
        "blue"
      } else if (brand == "HP") {
        "black"
      } else {
        "white"
      }

      // An instance method.
      def add(m: Int, n: Int): Int = m + n
    }
    println(new Calculator2("HP").color) //black


    //函数VS方法
    class C {
      var acc = 0

      def minc = {
        acc += 1
      }

      val finc = { () => acc += 1 }

    }
    val c = new C
    c.minc //calls c.minc()
    c.finc // returns the function as a value


    //继承
    class ScientificCalculator(brand: String) extends Calculator2(brand) {
      def log(m: Double, base: Double) = math.log(m) / math.log(base)
    }


    //重载方法
    class EvenMoreScientificCalculator(brand: String) extends ScientificCalculator(brand) {
      def log(m: Int): Double = log(m, math.exp(1))
    }


    //抽象类(你可以定义一个抽象类，它定义了一些方法但没有实现它们。取而代之是由扩展抽象类的子类定义这些方法。你不能创建抽象类的实例)
    abstract class Shape {
      def getArea(): Double // subclass should define this
    }
    class Circle(r: Int) extends Shape {
      def getArea(): Double = r * r * 3.14
    }


    //特质(Traits)
    //特质是一些字段和行为的集合，可以扩展或混入（mixin）你的类中
    //优先使用特质。一个类扩展多个特质是很方便的，但却只能扩展一个抽象类。
    //如果你需要构造函数参数，使用抽象类。因为抽象类可以定义带参数的构造函数，而特质不行。例如，你不能说trait t(i: Int) {}，参数i是非法的。
    trait Car {
      val brand: String
    }

    trait Shiny {
      val shineRefraction: Int
    }

    class BMW extends Car {
      val brand = "BMW"
    }

    //通过with关键字，一个类可以扩展多个特质
    class BMW2 extends Car with Shiny {
      val brand = "BMW"
      val shineRefraction = 12
    }


    //类型
    trait Cache[K, V] {
      def get(key: K): V

      def put(key: K, value: V)

      def delete(key: K)
    }


  }

}

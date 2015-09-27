package scalaschool

/**
 * Created by guilin1 on 15/8/1.
 * 类型和多态
 */
object TypeAndPolymorphism {

  def main(args: Array[String]) {

    /*
    Scala中的类型
Scala强大的类型系统拥有非常丰富的表现力。其主要特性有：

参数化多态性 粗略地说，就是泛型编程
（局部）类型推断 粗略地说，就是为什么你不需要这样写代码val i: Int = 12: Int
存在量化 粗略地说，为一些没有名称的类型进行定义
视窗 粗略地说，就是将一种类型的值“强制转换”为另一种类型
     */


    /*
    参数化多态性
     */
    val list1 = 1 :: 2 :: "foo" :: "bar" :: Nil
    def drop1[A](l: List[A]) = l.tail
    println(drop1(list1)) //List(2, foo, bar)


    /*
    变性 Variance

    Scala的类型系统必须同时解释类层次和多态性。类层次结构可以表达子类关系。在混合OO和多态性时，一个核心问题是：如果T’是T一个子类，
    Container[T’]应该被看做是Container[T]的子类吗？变性（Variance）注解允许你表达类层次结构和多态类型之间的关系：

      含义	               Scala               标记
      协变covariant	     C[T’]是 C[T] 的子类	 [+T]
      逆变contravariant	 C[T] 是 C[T’]的子类	 [-T]
      不变invariant	     C[T] 和 C[T’]无关	   [T]
     */
    class Covariant[+A]
    //right
    val cv: Covariant[AnyRef] = new Covariant[String]
    //wrong
    //    val fail: Covariant[String] = new Covariant[AnyRef]

    class Contravariant[-A]
    //right
    val cv2: Contravariant[String] = new Contravariant[AnyRef]
    //wrong
    //    val fail: Contravariant[AnyRef] = new Contravariant[String]

    class Animal {
      val sound = "rustle"
    }
    class Bird extends Animal {
      override val sound = "call"
    }
    class Chicken extends Bird {
      override val sound = "cluck"
    }
    val getTweet: (Bird => String) = ((a: Animal) => a.sound)

    val cv3: Covariant[Animal] = new Covariant[Bird]
    val cv4: Contravariant[Bird] = new Contravariant[Animal]


  }

}

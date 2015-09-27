package scalaschool

/**
 * Created by guilin1 on 15/7/28.
 * 集合
 */
object Collection {

  def timesTwo(i: Int): Int = i * 2

  def isEven(i: Int): Boolean = i % 2 == 0

  //基本数据结构
  def base(): Unit = {
    //List
    val students = List("张三", "李四", "王五", 11)
    println(students) //List(张三, 李四, 王五, 11)


    //Set(集没有重复)
    val students2 = Set("张三", "李四", "王五", 11, 11)
    println(students2) //Set(张三, 李四, 王五, 11)


    //元组 Tuple (元组是在不使用类的前提下，将元素组合起来形成简单的逻辑集合)
    val hostPort = ("localhost", 80, 32, "hello")
    println(hostPort) //(localhost,80,32,hello)
    println(hostPort._1) //localhost
    val hostPort2 = "localhost" -> 8080
    println(hostPort2) //(localhost,8080)


    //映射 Map (映射的值可以是映射甚或是函数)
    val map1 = Map("hello" -> "world")
    val map2 = Map("hello" -> Map("age" -> 22))
    val map3 = Map("timesTwo" -> timesTwo(3))
    println(map1, map2, map3) //(Map(hello -> world),Map(hello -> Map(age -> 22)),Map(timesTwo -> 6))
    println(map1("hello"), map1.get("hello")) //(world,Some(world))


    //选项 Option (Option 是一个表示有可能包含值的容器,Option本身是泛型的，并且有两个子类： Some[T] 或 None)
    /*
      trait Option[T] {
        def isDefined: Boolean
        def get: T
        def getOrElse(t: T): T
      }
     */
    val numbers = Map("one" -> 1, "two" -> 2)
    println(numbers.get("two")) //Some(2)
    println(numbers.get("three")) //None
    println(numbers.getOrElse("three", 3)) //3
  }

  //函数组合子
  def functionalCombinators(): Unit = {

    val list1 = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)

    //map (对列表中的每个元素应用一个函数，返回应用后的元素所组成的列表)
    println(list1.map(_ * 3 + 1)) //List(4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34, 37)
    println(list1.map(timesTwo _)) //List(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24)


    //foreach (foreach很像map，但没有返回值。foreach仅用于有副作用[side-effects]的函数)
    println(list1 foreach (_ * 2)) //()


    //filter (移除任何对传入函数计算结果为false的元素。返回一个布尔值的函数通常被称为谓词函数[或判定函数])
    println(list1.filter(_ % 3 == 0)) //List(3, 6, 9, 12)
    println(list1.filter(isEven _)) //List(2, 4, 6, 8, 10, 12)
    println(list1.filter((i: Int) => i % 2 == 0)) //List(2, 4, 6, 8, 10, 12)


    //zip (将两个列表的内容聚合到一个对偶列表中)
    val ziplist = List(1, 2, 3).zip(List("a", "b", "c"))
    println(ziplist) //List((1,a), (2,b), (3,c))
    println(List(1, 2, 3).zip(List("a", "b")).zip(List("c", "d", "e"))) //List(((1,a),c), ((2,b),d))


    //partition (将使用给定的谓词函数分割列表)
    println(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).partition(_ % 2 == 0)) //(List(2, 4, 6, 8, 10),List(1, 3, 5, 7, 9))


    //find (返回集合中第一个匹配谓词函数的元素)
    println(list1.find(_ > 5)) //Some(6)


    //drop (将删除前i个元素)
    println(list1.drop(4)); //List(5, 6, 7, 8, 9, 10, 11, 12)
    //dropWhile (将删除元素直到找到第一个不匹配谓词函数的元素)
    println(list1.dropWhile(_ % 2 != 0)) //List(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    println(list1.dropWhile(_ % 2 == 0)) //List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    println(list1.dropWhile(_ < 4)) //List(4, 5, 6, 7, 8, 9, 10, 11, 12)
    //dropRight (将删除右边i个元素)
    println(list1.dropRight(4)) //List(1, 2, 3, 4, 5, 6, 7, 8)


    println("--------------")
    //foldLeft
    println(list1.foldLeft(0)((m: Int, n: Int) => {
      println(m, n);
      m + n
    }))
    println("--------------")
    //foldRight
    println(list1.foldRight(0)((m: Int, n: Int) => {
      println(m, n);
      m + n
    }))
    println("--------------")


    val list2 = List(List(1, 2), List("hello", "world"))
    //flatten (将嵌套结构扁平化为一个层次的集合)
    println(list2.flatten) //List(1, 2, hello, world)


    //flatMap (是一种常用的组合子，结合映射[mapping]和扁平化[flattening]。 flatMap需要一个处理嵌套列表的函数，然后将结果串连起来)
    println(list2.flatMap(x => x.map(_ + "*"))) //List(1*, 2*, hello*, world*)
    val nestedNumbers = List(List(1, 2), List(3, 4))
    println(nestedNumbers.flatMap(x => x.map(_ * 2))) //List(2, 4, 6, 8)
    //可以把它看做是“先映射后扁平化”的快捷操作
    nestedNumbers.map((x: List[Int]) => x.map(_ * 2)).flatten


    //扩展函数组合子
    println("------------")
    def ourMap(numbers: List[Int], fn: Int => Int): List[Int] = {
      numbers.foldRight(List[Int]()) { (x: Int, xs: List[Int]) =>
        val l = fn(x) :: xs
        println("x:" + x + " xs:" + xs + "  fn(x):  " + l)
        l
        //        List[Int]()
      }
    }
    println(ourMap(list1, timesTwo(_))) //List(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24)
    println("------------")

    val extensions = Map("steve" -> 100, "bob" -> 101, "joe" -> 201)
    println(extensions.filter((namePhone: (String, Int)) => namePhone._2 < 200)) //Map(steve -> 100, bob -> 101)
    println(extensions.filter({ case (name, extension) => extension < 200 })) //Map(steve -> 100, bob -> 101)


  }

  def main(args: Array[String]) {

    base
    println
    functionalCombinators()

  }

}

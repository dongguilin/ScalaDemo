package scalaschool

/**
 * Created by guilin1 on 15/7/30.
 * 模式匹配和函数组合
 */
object Func {

  def main(args: Array[String]) {

    //函数组合

    def f(s: String) = "f(" + s + ")"
    def g(s: String) = "g(" + s + ")"

    //compose (compose 组合其他函数形成一个新的函数 f(g(x)))
    val fComposeG = f _ compose g _
    println(fComposeG("yay")) //f(g(yay))

    //andThen (andThen 和 compose很像，但是调用顺序是先调用第一个函数，然后调用第二个，即g(f(x)))
    val fAndThenG = f _ andThen g _
    println(fAndThenG("yay")) //g(f(yay))


    //PartialFunction (偏函数)
    val oneTwo: PartialFunction[Int, String] = {
      case 1 => "one"
      case 2 => "two"
    }
    println(oneTwo isDefinedAt 1) //true
    println(oneTwo isDefinedAt (2)) //true
    println(oneTwo isDefinedAt (3)) //false
    println(oneTwo(1)) //one
    val three: PartialFunction[Int, String] = {
      case 3 => "three"
    }
    val wildcard: PartialFunction[Int, String] = {
      case _ => "something else"
    }
    val partial = oneTwo orElse three orElse wildcard
    println(partial(1), partial(2), partial(3), partial(4)) //(one,two,three,something else)


    //case
    case class Person(name: String, age: Int)
    val extensions = List(Person("xiaoming", 22), Person("zhangsan", 40))
    println {
      extensions.filter { case Person(name, age) => age > 30 }
    } //List(Person(zhangsan,40))

  }

}

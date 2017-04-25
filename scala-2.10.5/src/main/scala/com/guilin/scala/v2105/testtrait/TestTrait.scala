package com.guilin.scala.v2105.testtrait

import java.io.{IOException, PrintWriter}
import java.util.Date

import org.scalatest.FunSuite

/**
  * Created by Administrator on 2017/4/23.
  */
class TestTrait extends FunSuite {

  trait Logger {
    def log(msg: String) //这是个抽象方法
  }

  trait Logged {
    def log(msg: String) {}
  }

  trait ConsoleLogger extends Logged {
    override def log(msg: String) {
      println(msg)
    }
  }

  trait FileLogger extends Logged {
    override def log(msg: String) {
      println("FileLogger " + msg)
    }
  }

  class SavingAccount extends Logged {
    def withdraw(amount: Double): Unit = {
      log("Insufficient funds")
    }
  }

  test("当做接口使用的特质") {
    class ConsoleLogger extends Logger {
      def log(msg: String): Unit = {
        //不需要写override
        println(msg)
      }
    }

    new ConsoleLogger().log("hello world")

    //添加多个特质，Logger、Cloneable、Serializable首先是一个整体，然后再由类来扩展
    class ConsoleLogger2 extends Logger with Cloneable with Serializable {
      def log(msg: String): Unit = {
        println("consolelogger2 " + msg)
      }
    }
    new ConsoleLogger2().log("hello world")
  }

  /**
    * 让特质拥有具体行为存在一个弊端。当特质改变时，所有混入了该特质的类都必须重新编译
    */
  test("带有具体实现的特质") {
    trait ConsoleLogger {
      def log(msg: String) {
        println(msg)
      }
    }

    class SavingAccount extends ConsoleLogger {
      def withdraw(amount: Double): Unit = {
        log("Insufficient funds")
      }
    }
  }

  test("带有特质的对象") {


    val acct = new SavingAccount with ConsoleLogger
    acct.log("hello acct")

    val act2 = new SavingAccount with FileLogger
    act2.log("hello acct2")
  }

  test("叠加在一起的特质") {
    //给所有日志消息添加时间戳
    trait TimestampLogger extends Logged {
      override def log(msg: String): Unit = {
        super.log(new Date() + " " + msg)
      }
    }
    //截断过于冗长的日志消息
    trait ShortLogger extends Logged {
      val maxLength = 15

      //特质中的字段
      override def log(msg: String): Unit = {
        super.log(if (msg.length <= maxLength) msg else msg.substring(0, maxLength - 3) + "...")
      }
    }

    //一般来说，特质从最后一个开始被处理
    val acct1 = new SavingAccount with ConsoleLogger with TimestampLogger with ShortLogger
    val acct2 = new SavingAccount with ConsoleLogger with ShortLogger with TimestampLogger
    acct1.log("He is playing the basketball.")
    acct2.log("He is playing the basketball.")
  }

  test("在特质中重写抽象方法") {

    trait TimestampLogger extends Logger {
      abstract override def log(msg: String): Unit = {
        super.log(new Date() + " " + msg)
      }
    }
  }

  /**
    * 在Scala中像这样在特质中使用具体和抽象方法十分普遍，在Java中，就需要声明一个接口和一个额外的扩展该接口的类
    */
  test("当做富接口使用的特质") {
    trait Logger {
      def log(msg: String)

      def info(msg: String) {
        log("INFO:" + msg)
      }

      def warn(msg: String) {
        log("WARN:" + msg)
      }

      def severe(msg: String) {
        log("SEVERE:" + msg)
      }
    }
    class SavingAccount extends Logger {
      def withdraw(amount: Double): Unit = {
        if (amount > 10) severe("Insufficient funds")
      }

      override def log(msg: String): Unit = {
        println(msg)
      }
    }
  }

  test("特质中的具体字段") {
    trait ShortLogger extends Logged {
      val maxLength = 15 //具体的字段,该字段不是被继承的，它只是简单地被加到了子类中
    }
  }

  /**
    * 特质中未被初始化的字段在具体的子类中必须被重写
    */
  test("特质中的抽象字段") {
    trait ShortLogger extends Logged {
      val maxLength: Int

      //抽象字段
      override def log(msg: String): Unit = {
        super.log(if (msg.length <= maxLength) msg else msg.substring(0, maxLength - 3) + "...")
      }
    }
    class SavingAccount extends ConsoleLogger with ShortLogger {
      val maxLength = 20
    }

    val acct = new SavingAccount()
    acct.log("hello zhangsan, this is a basketball")
  }

  /**
    * 构造器以如下顺序执行：
    * 1.首先调用超类的构造器
    * 2.特质构造器在超类构造器之后，类构造器之前执行
    * 3.特质由左到右被构造
    * 4.每个特质当中，父特质先被构造
    * 5.如果多个特质共有一个父特质，而那个父特质已经被构造，则不会被再次构造
    * 6.所有特质构造完毕，子类被构造
    */
  test("特质构造顺序") {
    trait FileLogger extends Logger {
      val out = new PrintWriter("app.log")
      out.println("# " + new Date().toString)

      def log(msg: String): Unit = {
        out.println(msg)
        out.flush()
      }
    }

    class Account

    trait ShortLogger extends Logger

    //构造器构造顺序：Account(超类)->Logger(第一个特质的父特质)->FileLogger(第一个特质)->ShortLogger(第二个特质,它的父特质Logger已被构造)->SavingAccount(类)
    class SavingAccount extends Account with FileLogger with ShortLogger

  }

  /**
    * 特质不能有构造器参数，每个特质都有一个无参数的构造器，缺少构造器参数是特质与类之间唯一的技术差别
    */
  test("初始化特质中的字段") {
    //FileLogger有一个抽象的字段filename来存放文件名
    trait FileLogger extends Logger {
      val filename: String
      val out = new PrintWriter(filename)

      def log(msg: String): Unit = {
        out.println(msg)
        out.flush()
      }
    }

    //wrong,FileLogger的构造器先于子类构造器执行，会报空指针异常
    //val acct1 = new SavingAccount with FileLogger{val filename = "myapp.log"}

    class Account

    //解决办法两种:1.提前定义；2.使用懒值


    //1.提前定义
    val acct1 = new {
      //new之后的提前定义块，提前定义发生在常规的构造序列之前，在FileLogger被构造时，filename已经是初始化过的了
      val filename = "acct1.log"
    } with Account with FileLogger
    acct1.log("hello")

    class SavingAccount2 extends {
      //extends后是提前定义块
      val filename = "acct2.log"
    } with Account with FileLogger {
      //SavingAccount2的实现
    }
    val acct2 = new SavingAccount2
    acct2.log("hello")

    //2.在FileLogger2构造器中使用懒值
    trait FileLogger2 extends Logger {
      val filename: String
      lazy val out = new PrintWriter(filename)

      def log(msg: String): Unit = {
        out.println(msg)
      }
    }

    val acct3 = new FileLogger2 {
      override val filename: String = "acct3.txt"
    }
    acct3.log("hello")
  }

  /**
    * 特质也可以扩展类，这个类将会自动成为所有混入该特质的超类
    */
  test("扩展类的特质") {
    trait LoggedException extends Exception with Logged {
      //log是特质Logged的方法，getMessage是从Exception继承下来的方法
      def log() {
        log(getMessage)
      }
    }

    //创建一个混入LoggedException特质的类
    class UnhappyException extends LoggedException {
      override def getMessage: String = "arggh!"
    }

    //right IOException是Exception的子类
    class UnhappyException2 extends IOException with LoggedException

    //wrong 无法同时将JFrame和Exception作为超类
    //class UnhappyException3 extends JFrame with LoggedException
  }

  /**
    * 1.scala可以使用自身类型保证所有混入该特质的类都认这个类作超类
    * 2.带有自身类型的特质和带有超类型的特质很相似，两种情况都能确保混入该特质的类能够使用某个特定类型的特性
    * 3.在某些情况下自身类型比超类型版的特质更灵活，自身类型可以解决特质间的循环依赖
    * 4.自身类型可以处理结构类型(这种类型只给出类必须拥有的方法，而不是类的名称)
    */
  test("自身类型") {
    //该特质并不扩展Exception类，而是有一个自身类型Exception，这意味着它只能被混入Exception的子类
    trait LoggedException extends Logged {
      this: Exception =>
      def log() {
        //在特质的方法中，可以调用该自身类型的任何方法
        log(getMessage)
      }

      def log2(): Unit = {
        println("log2")
      }
    }

    //这个特质可以被混入拥有getMessage方法的类
    trait LoggedExceptino2 extends Logged {
      this: {def getMessage(): String} =>
      def log() {
        log(getMessage())
      }
    }
  }

  /**
    * scala需要将特质翻译成JVM的类和接口
    */
  test("特质原理") {
    //只有抽象方法的特质被简单变成一个java接口
    trait Logger {
      def log(msg: String)
    }
    //被翻译为
    /*public interface Logger{
      void log(String msg);
    }*/

    //如是特质有具体方法，scala会创建出一个伴生类，该伴生类用静态方法存放特质的方法
    trait ConsoleLogger extends Logger {
      def log(msg: String) {
        println(msg)
      }
    }
    //被翻译为
    /*public interface ConsoleLogger extends Logger{
      void log(String msg);
    }
    public class ConsoleLogger$class{//生成java伴生类
      public static void log(ConsoleLogger self, String msg){
        println(msg);
      }
    }*/

    //伴生类不会有任何字段，特质中的字段对应到接口中的抽象的getter和setter方法
    trait ShortLogger extends Logger {
      //当特质被混入类的时候，类将会得到一个带有getter和setter的maxLength字段，那个类的构造器会调用初始化方法
      val maxLength = 15 //具体的字段，字体的初始化发生在伴生类的一个初始化方法内
    }
    //被翻译成
    /*public interface ShortLogger extends Logger{
      public abstract int maxLength();
      public abstract void weird_prefix$maxLength_$eq(int);
    }
    public class ShortLogger$class{
      public void $init$(ShortLogger self){
        self.weird_prefix$maxLength_$eq(15);
      }
    }*/

    //如果特质扩展自某个超类，则伴生类并继承这个超类，该超类会被任何实现该特质的类继承

  }


}


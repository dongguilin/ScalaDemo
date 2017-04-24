package com.guilin.scala.v2105.testtrait

import java.io.PrintWriter
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
    trait FileLogger extends Logger {
      val filename: String
      val out = new PrintWriter(filename)

      def log(msg: String): Unit = {
        out.println(msg)
        out.flush()
      }
    }

    class Account

    //wrong,FileLogger的构造器先于子类构造器执行，会报空指针异常
    //val acct1 = new SavingAccount with FileLogger{val filename = "myapp.log"}

    //解决办法两种:1.提前定义；2.使用懒值

    //1.提前定义
    val acct = new {
      val filename = "myapp.log"
    } with SavingAccount with FileLogger

    class SavingAccount2 extends {
      //extends后是提前定义块
      val filename = "myapp.log"
    } with Account with FileLogger {
      //SavingAccount2的实现
    }

    //2.在FileLogger2构造器中使用懒值
    trait FileLogger2 extends Logger {
      val filename: String
      lazy val out = new PrintWriter(filename)

      def log(msg: String): Unit = {
        out.println(msg)
      }
    }

  }


}


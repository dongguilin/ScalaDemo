package dt.scala.basics

import java.io.{FileInputStream, File, PrintWriter}
import java.nio.file.{FileVisitResult, SimpleFileVisitor, Path}
import java.util.Objects

import scala.io.Source

/**
 * Created by hadoop on 2015/10/7.
 * Scala中文件的读取、写入、控制台输入
 */
object FileOps {

  //遍历某目录下所有子目录
  def subdirs(dir: File): Iterator[File] = {
    val children = dir.listFiles.filter(_.isDirectory)
    children.toIterator ++ children.toIterator.flatMap(subdirs _)
  }

  def main(args: Array[String]) {

    //读取行
    val source = Source.fromFile("E:/Test/scala/test1.txt")

    //    for (line <- source.getLines) {
    //      println(line)
    //    }
    //    println(source.getLines().toArray.map(println))
    //    println(source.getLines().mkString)
    //    println(source.getLines().mkString.split("\\s+").map(println))

    source.close

    //    val webSource = Source.fromURL("http://spark.apache.org/")
    //    webSource.foreach(print)
    //    webSource.close

    //从标准输入读取
    //    Source.stdin

    //从字符串中
    Source.fromString("Hello, World!")
    val tokens = Source.fromString("11 12.3  2 0.5").mkString.split("\\s+")
    //    val numbers = for(w <- tokens) yield w.toDouble
    val numbers = tokens.map(_.toDouble)
    println(numbers.foreach(println))

    //读取二进制文件(Scala没有提供读取二进制文件的方法，需要使用java类库)
    //    val file = new File("E:/Test/scala/test1.txt")
    //    val in = new FileInputStream(file)
    //    val bytes = new Array[Byte](file.length().toInt)
    //    in.read(bytes)
    //    in.close()

    //访问目录
//    subdirs(new File("E:\\WorkSpace\\IdeaProjects\\ScalaDemo")).toList.map(println(_))

    import java.nio.file._
    //隐式转换
    implicit def makeFileVisitor(f: (Path) => Unit) = new SimpleFileVisitor[Path] {
      override def visitFile(p: Path, attrs: attribute.BasicFileAttributes) = {
        Objects.requireNonNull(p)
        Objects.requireNonNull(attrs)

        f(p)
        FileVisitResult.CONTINUE
      }
    }
    Files.walkFileTree(new File("E:\\WorkSpace\\IdeaProjects\\ScalaDemo").toPath, (f: Path) => println(f))

    //写入(Scala没有内建对写入文件的支持，要写入文本文件，可使用java.io.PrintWriter)
    //    val writer = new PrintWriter(new File("E:/Test/scala/scalaFile.txt"))
    //    for (i <- 1 to 100) writer.println(i)
    //    writer.close()

    //控制台输入
    //    print("Please enter your input : ")
    //    val line = readLine
    //    println("Thanks, you just typed: " + line)


  }

}

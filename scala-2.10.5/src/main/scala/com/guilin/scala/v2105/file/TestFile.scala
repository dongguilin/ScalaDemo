package com.guilin.scala.v2105.file

import java.io.{File, FileInputStream, PrintWriter}
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.{FileVisitResult, Files, Path, SimpleFileVisitor}

import org.scalatest.FunSuite

import scala.io.Source

/**
  * Created by Administrator on 2017/4/25.
  * Source类扩展自Iterator[Char]
  */
class TestFile extends FunSuite {

  val filePath = this.getClass.getResource("myfile.txt").getPath


  test("读取行 getLines") {
    val source = Source.fromFile(filePath, "UTF-8")
    val lineIterator = source.getLines()
    for (l <- lineIterator) {
      println(l)
    }
    source.close
  }

  test("读取行 mkString") {
    val source = Source.fromFile(filePath, "UTF-8")
    val contents = source.mkString
    println(contents)
    source.close()
  }

  test("读取字符") {
    val source = Source.fromFile(filePath, "UTF-8")
    for (c <- source) print(c)
    source.close
  }

  test("读取字符 buffered 使用head查看字符") {
    val source = Source.fromFile(filePath, "UTF-8")
    val iter = source.buffered
    while (iter.hasNext) {
      if (iter.head == 'l') {
        print(iter.next() + "-")
      } else {
        print(iter.next())
      }
    }
    source.close()
  }

  test("读取词法单元和数字") {
    val source = Source.fromFile(this.getClass.getResource("number.txt").getPath, "UTF-8")
    val tokens = source.mkString.split("\\s+")
    val numbers = for (w <- tokens) yield w.toDouble
    //或者
    //val numbers = tokens.map(_.toDouble)

    println(numbers.mkString("\n"))
    source.close
  }

  test("从URL或其他源读取") {
    val source1 = Source.fromURL("https://www.baidu.com/", "UTF-8")
    println(source1.mkString)
    source1.close

    //val source = Source.stdin
    //println(source.mkString)
  }

  /**
    * Scala并没有提供读取二进制文件的方法，需要使用java类库
    */
  test("读取二进制文件") {
    val file = new File(filePath)
    val in = new FileInputStream(file)
    val bytes = new Array[Byte](file.length().toInt)
    in.read(bytes)
    println(new String(bytes))
    in.close()
  }

  /**
    * Scala没有内建对写入文件的支持，要写入文件，可使用java.io.PrintWriter
    */
  test("写入文本文件") {
    val tmpDir = System.getProperty("java.io.tmpdir");
    val out = new PrintWriter(tmpDir + File.separator + "a.tmp")
    for (i <- 1 to 100) out.println(i)
    out.close()
  }

  test("访问目录") {
    //遍历某目录下所有子目录
    def subdirs(dir: File): Iterator[File] = {
      val childen = dir.listFiles.filter(_.isDirectory)
      childen.toIterator ++ childen.toIterator.flatMap(subdirs(_))
    }

    val dir = new File("d:/test")
    for (d <- subdirs(dir)) println(d)
  }

  test("访问目录 Files.walkFileTree") {
    val dir = new File("d:/test")

    implicit def makeFileVisitor(f: (Path) => Unit) = new SimpleFileVisitor[Path] {
      override def visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult = {
        f(file)
        FileVisitResult.CONTINUE
      }
    }

    Files.walkFileTree(dir.toPath, (f: Path) => println(f))
  }

}

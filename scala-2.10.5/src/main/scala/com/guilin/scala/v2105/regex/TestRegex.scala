package com.guilin.scala.v2105.regex

import org.scalatest.FunSuite

/**
  * Created by Administrator on 2017/4/29.
  */
class TestRegex extends FunSuite {

  test("正则表达式") {
    val str = "99 bottles, 98 bottles"
    val numPattern = "[0-9]+".r
    //正则表达式包含反斜杠或引号时，可使用“原始”字符串语法"""..."""
    val wsnumwsPattern =
      """\s+[0-9]+\s+""".r
    for (matchString <- numPattern.findAllIn(str)) print(matchString + " ")

    //找到字符串中的首个匹配项，用findFirstIn
    println(wsnumwsPattern.findFirstIn(str)) //Some( 98 )

    //检查某个字符串的开始部分是否能匹配，用findPrefixOf
    println(numPattern.findPrefixOf(str)) //Some(99)
    println(wsnumwsPattern.findPrefixOf(str)) //None

    //替换首个匹配项
    assertResult("XX bottles, 98 bottles")(numPattern.replaceFirstIn(str, "XX"))
    //替换全部匹配项
    assertResult("XX bottles, XX bottles")(numPattern.replaceAllIn(str, "XX"))
  }

  test("正则表达式组") {
    val numitemPattern = "([0-9]+) ([a-z]+)".r
    val numitemPattern(num, item) = "99 bottles"
    assertResult("99")(num)
    assertResult("bottles")(item)

    val str = "99 bottles, 98 bottles"
    for (numitemPattern(num, item) <- numitemPattern.findAllIn(str)) println(num, item)
  }

}

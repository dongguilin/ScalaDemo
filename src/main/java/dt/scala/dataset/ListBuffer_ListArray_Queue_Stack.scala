package dt.scala.dataset

/**
 * Created by hadoop on 2015/10/14.
 * ListBuffer、ArrayBuffer、Queue、Stack操作
 */
object ListBuffer_ListArray_Queue_Stack {

  def main(args: Array[String]) {

    import scala.collection.mutable.ListBuffer
    val listBuffer = new ListBuffer[Int]
    listBuffer += 1
    listBuffer += 2
    println(listBuffer) //ListBuffer(1, 2)

    import scala.collection.mutable.ArrayBuffer
    val arrayBuffer = new ArrayBuffer[Int]
    arrayBuffer += 1
    arrayBuffer += 2
    println(arrayBuffer) //ArrayBuffer(1, 2)

    import scala.collection.immutable.Queue
    val empty = Queue[String]()
    val queue1 = empty.enqueue("a")
    val queue2 = empty.enqueue(List("b", "c", "d"))
    println(queue1) //Queue(a)
    println(queue2) //Queue(b, c, d)

    val (element, left) = queue2.dequeue
    println(element + " : " + left) //b : Queue(c, d)
    println(queue2) //Queue(b, c, d)

    val queue = scala.collection.mutable.Queue[String]()
    queue += "a"
    queue ++= List("b", "c")
    println(queue) //Queue(a, b, c)
    queue.dequeue
    println(queue) //Queue(b, c)

    import scala.collection.mutable.Stack
    val stack = Stack[Int]()
    stack.push(1)
    stack.push(2)
    stack.push(3)
    println(stack) //Stack(3, 2, 1)
    println(stack.top) //3
    println(stack) //Stack(3, 2, 1)
    println(stack.pop) //3
    println(stack) //Stack(2, 1)

  }

}

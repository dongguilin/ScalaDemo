package dt.scala.function

import java.awt.event.{ActionListener, ActionEvent}
import javax.swing.{JButton, JFrame}

/**
 * Created by hadoop on 2015/10/7.
 * Scala中SAM转换
 */
object SAM {

  def main(args: Array[String]) {
    var data = 0
    val frame = new JFrame("SAM Testing");
    val jButton = new JButton("Counter")
    //	jButton.addActionListener(new ActionListener {
    //	  override def actionPerformed(event: ActionEvent) {
    //	    data += 1
    //	    println(data)
    //	  }
    //	})

    implicit def convertedAction(actionFunc: (ActionEvent) => Unit) =
      new ActionListener {
        override def actionPerformed(event: ActionEvent) {
          actionFunc(event)
        }
      }

    jButton.addActionListener((event: ActionEvent) => {
      data += 1
      println(data)
    })

    frame.setContentPane(jButton);
    frame.pack();
    frame.setVisible(true);
  }

}

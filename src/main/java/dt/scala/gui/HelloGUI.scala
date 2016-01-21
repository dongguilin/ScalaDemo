package dt.scala.gui

import scala.swing.{Button, Frame, MainFrame, SimpleSwingApplication}

/**
 * Created by hadoop on 2015/11/15.
 * Scala界面GUI
 */
object HelloGUI extends SimpleSwingApplication {
  override def top: Frame = new MainFrame {
    title = "Hello GUI"
    contents = new Button() {
      text = "Scala=>Spark!!!"
    }
  }
}

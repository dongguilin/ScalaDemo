package dt.scala.gui

import scala.swing._
import scala.swing.event.ButtonClicked

/**
 * Created by hadoop on 2016/1/21.
 * Scala界面Panel、Layout、界面事件处理
 */
object GUI_Panel_Layout extends SimpleSwingApplication {
  override def top: Frame = new MainFrame {
    title = "Second GUI"
    val button = new Button {
      text = "Scala"
    }
    val label = new Label {
      text = "Here is Spark"
    }
    contents = new BoxPanel(Orientation.Vertical) {
      contents += button
      contents += label
      border = Swing.EmptyBorder(50, 50, 50, 50)
    }

    listenTo(button)
    var clicks = 0
    reactions += {
      case ButtonClicked(button) => {
        clicks += 1
        label.text = "Clicked " + clicks + " times"
      }
    }

  }
}

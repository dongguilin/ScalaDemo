package dt.scala.gui

import java.io.File

import scala.swing._
import scala.swing.event.ButtonClicked

/**
 * Created by hadoop on 2016/1/21.
 * Scala界面和事件处理
 */
object GUI_Event extends SimpleSwingApplication {

  val fileChooser = new FileChooser(new File("c:/"))
  fileChooser.title = "File chooser"

  val button = new Button {
    text = "Choose a file from local"
  }

  val label = new Label {
    text = "no file selected yet"
  }

  val button2 = new Button {
    text = "other button"
  }

  val mainPanel = new FlowPanel {
    contents += button
    contents += label
    contents += button2
  }

  override def top: Frame = new MainFrame {
    title = "Scala GUI Programing advanced!!!"
    contents = mainPanel

    listenTo(button, button2)

    reactions += {
      case ButtonClicked(b) => {
        if (b == button2) {
          println("hehe")
        }
        val result = fileChooser.showOpenDialog(mainPanel)
        if (result == FileChooser.Result.Approve) {
          label.text = fileChooser.selectedFile.getPath
        }
      }
    }
  }
}

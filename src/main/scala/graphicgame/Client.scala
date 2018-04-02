package graphicgame

import java.rmi.server.UnicastRemoteObject
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.image.ImageView
import scalafx.scene.image.Image
import scalafx.scene.canvas._
import scalafx.animation.AnimationTimer
import scalafx.scene.input._
import scalafx.application.Platform
import sun.java2d.pisces.Renderer

object Client extends UnicastRemoteObject with JFXApp with RemoteClient {
  val canvas = new Canvas(1000, 800)
  val gc = canvas.graphicsContext2D
  val myRenderer = new Renderer2D(gc, 30)

  val server = java.rmi.Naming.lookup("rmi://localhost:1099/ConnorServer") match {
    case rs: RemoteServer => rs
  }

  val player = server.connect(this)

  var spacePressed = false

  stage = new JFXApp.PrimaryStage {
    title = "Connor's Game" // Change this to match the theme of your game.
    scene = new Scene(1000, 800) {

      content = canvas

      onKeyPressed = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Up => player.moveUpPressed
          case KeyCode.Down => player.moveDownPressed
          case KeyCode.Left => player.moveLeftPressed
          case KeyCode.Right => player.moveRightPressed
          case KeyCode.Space => player.fireUpPressed
          case _ =>
        }
      }
      onKeyReleased = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Up => player.moveUpReleased
          case KeyCode.Down => player.moveDownReleased
          case KeyCode.Left => player.moveLeftReleased
          case KeyCode.Right => player.moveRightReleased
          case KeyCode.Space => player.fireUpReleased
          case _ =>
        }
      }
    }
  }

  def updateLevel(lvl: PassableLevel, x: Double, y: Double): Unit = {
    Platform.runLater {
      myRenderer.render(lvl, x, y)
    }
  }
}
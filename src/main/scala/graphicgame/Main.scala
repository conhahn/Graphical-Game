package graphicgame

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.image.ImageView
import scalafx.scene.image.Image
import scalafx.scene.canvas._
import scalafx.animation.AnimationTimer
import scalafx.scene.input._

/**
 * This is a stub for the graphical game.
 */

object Main extends JFXApp {
  val level = new Level
  var player = new Player(0.0, 0.0, level, false)
  var enemy = new Enemy(10.0, 20.0, level, false, -1)

  stage = new JFXApp.PrimaryStage {
    title = "Connor's Game" // Change this to match the theme of your game.
    scene = new Scene(1000, 800) {
      val canvas = new Canvas(1000, 800)
      val gc = canvas.graphicsContext2D
      content = canvas
      val myRenderer = new Renderer2D(gc, 30)

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
      myRenderer.render(level, player.x, player.y)
      //      val timer: AnimationTimer = AnimationTimer(time => {
      //
      //      })
      //      timer.start
    }
  }
}
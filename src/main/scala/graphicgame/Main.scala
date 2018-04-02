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

  var player = new Player(9, 9, level, false)
  var enemy = new Enemy(10.0, 20.0, level, false)
  var spacePressed = false

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
      var lastTime = 0L
      val timer = AnimationTimer(time => {
        if (lastTime > 0) {
          val delay = (time - lastTime) / 1e9
          level.updateAll(delay)
        }
        lastTime = time
        myRenderer.render(level.buildPassable, player.x, player.y)
      })
      timer.start()
    }
  }
}
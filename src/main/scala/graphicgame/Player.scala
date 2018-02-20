package graphicgame

class Player(private var _x: Double, private var _y: Double, level: Level, movingUp: Boolean) extends Entity {
  def x: Double = _x
  def y: Double = _y
  def width: Double = 6.9
  def height: Double = 6.9

  var upPressed = false
  var downPressed = false
  var leftPressed = false
  var rightPressed = false

  def update(delay: Double): Unit = {
    if (upPressed == true) {
      _y = _y + 0.5
    } else if (downPressed == true) {
      _y = _y - 0.5
    } else if (rightPressed == true) {
      _x = _x + 0.5
    } else if (leftPressed == true) {
      _x = _x + 0.5
    }
  }
  def postCheck(): Unit = {
    ???
  }

  def stillHere(): Boolean = {
    if (x > 0.0) {
      true
    } else false
  }

  def moveUpPressed(): Unit = {
    upPressed = true
  }

  def fireUpPressed(): Unit = {
    ???
  }

  def moveRightPressed(): Unit = {
    rightPressed = true
  }

  def moveLeftPressed(): Unit = {
    leftPressed = true
  }

  def moveDownPressed(): Unit = {
    downPressed = true
  }

  def moveUpReleased(): Unit = {
    upPressed = false
  }

  def fireUpReleased(): Unit = {
    ???
  }

  def moveRightReleased(): Unit = {
    rightPressed = false
  }

  def moveLeftReleased(): Unit = {
    leftPressed = false
  }

  def moveDownReleased(): Unit = {
    downPressed = false
  }
}


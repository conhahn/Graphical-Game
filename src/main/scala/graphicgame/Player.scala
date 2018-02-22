package graphicgame

class Player(private var _x: Double, private var _y: Double, val level: Level, movingUp: Boolean) extends Entity {
  level += this
  def x: Double = _x
  def y: Double = _y
  def width: Double = 1
  def height: Double = 1

  var upPressed = false
  var downPressed = false
  var leftPressed = false
  var rightPressed = false

  def update(delay: Double): Unit = {
    if (upPressed == true) {
      moveEntity(0,5*delay*(-1))
    } else if (downPressed == true) {
      moveEntity(0,5*delay)
    } else if (rightPressed == true) {
      moveEntity(5*delay,0)
    } else if (leftPressed == true) {
      moveEntity(5*delay*(-1),0)
    }
  }
  
  def moveEntity(dx: Double, dy: Double): Unit = {
    if(level.maze.isClear(x+dx, y+dy, width, height)){
      _y = _y + dy
      _x = _x + dx
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


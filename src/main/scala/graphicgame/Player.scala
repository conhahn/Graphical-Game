package graphicgame

import java.rmi.server.UnicastRemoteObject

class Player(private var _x: Double, private var _y: Double, val level: Level, private var dead: Boolean) extends UnicastRemoteObject with Entity with RemotePlayer {
  while (!level.maze.isClear(_x, _y, width, height)) {
    _x += 1
    _y += 1
  }

  level += this
  def x: Double = _x
  def y: Double = _y
  def width: Double = 1.0
  def height: Double = 1.0

  var upPressed = false
  var downPressed = false
  var leftPressed = false
  var rightPressed = false
  var spacePressed = false

  def update(delay: Double): Unit = {
    println(x,y,upPressed,downPressed,leftPressed)
    if (upPressed == true) {
      moveEntity(0, 5 * delay * (-1))
    } else if (downPressed == true) {
      moveEntity(0, 5 * delay)
    } else if (rightPressed == true) {
      moveEntity(5 * delay, 0)
    } else if (leftPressed == true) {
      moveEntity(5 * delay * (-1), 0)
    } else if (spacePressed == true)
      new Bullet(_x, _y, level, false)
  }

  def moveEntity(dx: Double, dy: Double): Unit = {
    if (level.maze.isClear(x + dx, y + dy, width, height)) {
      _y = _y + dy
      _x = _x + dx
    }
  }

  def postCheck(e: Seq[Entity]): Unit = {
    if (e.map(a => if (a != a) Entity.intersect(a, this)).contains(true)) {
      dead = true
    }
  }

  def stillHere(): Boolean = {
    !dead
  }

  def moveUpPressed(): Unit = {
    upPressed = true
  }

  def fireUpPressed(): Unit = {
    spacePressed = true
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
    spacePressed = false
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

  def buildPassable(): PassableEntity = {
    PassableEntity(Entity.EntityType.Player,x,y,width,height)
  }
}


package graphicgame

class Enemy(private var _x: Double, private var _y: Double, val level: Level, dead: Boolean, dir: Int) extends Entity {
  level += this
  def x: Double = _x
  def y: Double = _y
  def width: Double = 1
  def height: Double = 1

  def moveEntity(dx: Double, dy: Double): Unit = {
    if (level.maze.isClear(x + dx, y + dy, width, height)) {
      _y = _y + dy
      _x = _x + dx
    }
  }

  def update(delay: Double): Unit = {
    moveEntity(math.random()*delay,0)
    moveEntity(0,math.random()*delay)
  }

  def postCheck(): Unit = {
    ???
  }

  def stillHere(): Boolean = {
    dead == false
  }
}
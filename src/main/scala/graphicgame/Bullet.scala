package graphicgame

class Bullet(private var _x: Double, private var _y: Double, val level: Level, private var dead: Boolean) extends Entity {
  level += this

  def x: Double = _x
  def y: Double = _y
  def width: Double = 1.5
  def height: Double = 1.0

  var count = 0
  def update(delay: Double): Unit = {
    if (count < 300) {
      moveEntity((-2) * delay)
      count += 1
    } else dead = true
  }

  def moveEntity(dy: Double): Unit = {
    if (level.maze.isClear(x, y + dy, width, height)) {
      _y = _y + dy
    }
  }

  def postCheck(e: Seq[Entity]): Unit = {
    if (e.map(a => if(a != a) Entity.intersect(a, this)).contains(true)) {
      dead = true
    }
  }

  def stillHere(): Boolean = {
    !dead
  }

}
package graphicgame

class Enemy(private var _x: Double, private var _y: Double, level: Level, dead: Boolean, dir: Int) extends Entity {
  def x: Double = _x
  def y: Double = _y
  def width: Double = 6.9
  def height: Double = 6.9

  def update(delay: Double): Unit = {
    ???
  }

  def postCheck(): Unit = {
    ???
  }

  def stillHere(): Boolean = {
    dead == false
  }
}
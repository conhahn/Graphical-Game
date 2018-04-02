package graphicgame

trait Entity {
  def x: Double
  def y: Double
  def width: Double
  def height: Double
  def update(delay: Double): Unit
  def postCheck(e: Seq[Entity]): Unit
  def stillHere(): Boolean
  def buildPassable(): PassableEntity
}

object Entity {
  def intersect(e1: Entity, e2: Entity): Boolean = {
    if (e1.x + e1.width > e2.x && e1.x < e2.x + e2.width && e1.y + e1.height > e2.y && e1.y < e2.y + e2.height) {
      true
    } else false
  }
 
  object EntityType extends Enumeration {
   val Player, Enemy, Bullet = Value
 }
}
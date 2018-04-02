package graphicgame
import collection.mutable

class Level {
  val maze = Maze(5, true, 30, 30, 0.7)
  private var _entities = mutable.Buffer[Entity]()

  def entities: Seq[Entity] = {
    _entities
  }

  def +=(e: Entity): Unit = {
    _entities += e
  }

  def -=(e: Entity): Unit = {
    _entities -= e
  }

  def updateAll(delay: Double): Unit = {
    //println(_entities)
    _entities.foreach(_.update(delay))
    _entities.foreach(_.postCheck(_entities))
    _entities = _entities.filter(_.stillHere() == true)
  }

  def players(): Seq[Player] = entities.collect {
    case p: Player => p
  }

  def buildPassable: PassableLevel = {
    PassableLevel(maze,_entities.map(_.buildPassable))
  }
  
  
}
package graphicgame

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class Enemy(private var _x: Double, private var _y: Double, val level: Level,private var dead: Boolean) extends Entity {
  while (!level.maze.isClear(_x, _y, width, height)) {
    _x += 1
    _y += 1
  }

  level += this
  def x: Double = _x
  def y: Double = _y
  def width: Double = 1
  def height: Double = 1

  def moveEntity(delay: Double): Unit = {
    val a = bfs(level.players()(0), _x + 1, _y, level.players()(0).x, level.players()(0).y)
    val b = bfs(level.players()(0), _x, _y + 1, level.players()(0).x, level.players()(0).y)
    val c = bfs(level.players()(0), _x - 1, _y, level.players()(0).x, level.players()(0).y)
    val d = bfs(level.players()(0), _x, _y - 1, level.players()(0).x, level.players()(0).y)

    //println((a, b, c, d))

    if ((a, b, c, d) == (-1, -1, -1, -1)) {

    } else if (a <= Array(b, c, d).filter(_ != (-1.0)).min && level.maze.isClear(_x + 1, _y, width, height)) {
      _x += 1.0 * delay
    } else if (b <= Array(a, c, d).filter(_ != (-1.0)).min && level.maze.isClear(_x, _y + 1, width, height)) {
      _y += 1.0 * delay
    } else if (c <= Array(a, b, d).filter(_ != (-1.0)).min && level.maze.isClear(_x - 1, _y, width, height)) {
      _x -= 1.0 * delay
    } else if (d <= Array(a, b, c).filter(_ != (-1.0)).min && level.maze.isClear(_x, _y - 1, width, height)) {
      _y -= 1.0 * delay
    }
  }

  //breadth-first search
  val offsets = List((1.0, 0.0), (-1.0, 0.0), (0.0, 1.0), (0.0, -1.0))
  def bfs(e2: Player, sx: Double, sy: Double, ex: Double, ey: Double): Double = {
    val queue = new ArrayQueue[(Double, Double, Double)]
    var visited = Set[(Double, Double)]()
    queue.enqueue((sx, sy, 0.0))
    visited += (sx -> sy)
    while (!queue.isEmpty) {
      //println(queue.length)
      val (x_, y_, steps) = queue.dequeue()
      //println("x_: " + x_ + ", y_: " + y_)
      for ((dx, dy) <- offsets) {
        val (nx, ny) = (x_ + dx, y_ + dy)
        if (Entity.intersect(this, e2)) {
          //println("intersect")
          //println(e2.x == ex)
          //println(e2.y == ey)
          return steps + 1.0
        }
        if (nx >= 0.0 && nx <= level.maze.width.toDouble && ny >= 0.0 && ny <= level.maze.height.toDouble && level.maze.isClear(ny, ny, width, height) && !visited(nx -> ny)) {
          queue.enqueue((nx, ny, steps + 1.0))
          //println(queue.length)
          visited += (nx -> ny)
        }
      }
    }
    -1.0
  }

  def update(delay: Double): Unit = {
    moveEntity(delay)
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
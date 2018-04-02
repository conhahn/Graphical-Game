package graphicgame

import java.rmi.server.UnicastRemoteObject

case class GameInfo(client: RemoteClient, player: Player)

object Server extends UnicastRemoteObject with App with RemoteServer {
  java.rmi.registry.LocateRegistry.createRegistry(1099)
  java.rmi.Naming.rebind("ConnorServer", this)
  val level = new Level
  val enemy = new Enemy(10.0, 20.0, level, false)

  private var games = List[GameInfo]()

  def connect(client: RemoteClient): RemotePlayer = {
    val player = new Player(9.0, 9.0, level, false)
    games ::= GameInfo(client, player)

    println("adding player")

    player
  }

  var lastTime = 0L
  var counter = 0.0
  val interval = 0.1
  while (true) {
    val time = System.nanoTime()
    if (lastTime > 0) {
      val delay = (time - lastTime) / 1e9
      level.updateAll(delay)
      counter += delay
      if (counter > interval) {
        //println("sending level")
        val game = level.buildPassable
        for (GameInfo(client, p) <- games) {
          client.updateLevel(game, p.x, p.y)
        }
        counter = 0.0
      }
    }
    lastTime = time
  }
}
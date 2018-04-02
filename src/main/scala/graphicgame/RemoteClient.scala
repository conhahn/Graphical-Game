package graphicgame

@remote trait RemoteClient {
  def updateLevel(lvl: PassableLevel, x: Double, y: Double): Unit
}
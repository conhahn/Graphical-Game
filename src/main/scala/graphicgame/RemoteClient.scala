package graphicgame

@remote trait RemoteClient {
  def updateLevel(lvl:PassableLevel):Unit
}
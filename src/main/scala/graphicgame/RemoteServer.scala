package graphicgame

trait RemoteServer {
  def connect(client: RemoteClient): RemotePlayer

}
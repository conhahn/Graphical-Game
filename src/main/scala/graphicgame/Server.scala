package graphicgame

import java.rmi.server.UnicastRemoteObject

object Server extends UnicastRemoteObject with App with RemoteServer {
  def connect(client: RemoteClient): RemotePlayer = {
    ???
  }
}
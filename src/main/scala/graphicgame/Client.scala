package graphicgame

import java.rmi.server.UnicastRemoteObject
import scalafx.application.JFXApp

object Client extends UnicastRemoteObject with JFXApp with RemoteClient {
  def updateLevel(lvl:PassableLevel):Unit = {
    ???
  }
}
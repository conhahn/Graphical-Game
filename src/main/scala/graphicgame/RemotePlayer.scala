package graphicgame

trait RemotePlayer {
  def upPressed(): Unit
  def upReleased(): Unit
  def downPressed(): Unit
  def downReleased(): Unit
  def leftPressed(): Unit
  def leftReleased(): Unit
  def rightPressed(): Unit
  def rightReleased(): Unit
  def fireUpPressed(): Unit
  def fireUpReleased(): Unit
  
}
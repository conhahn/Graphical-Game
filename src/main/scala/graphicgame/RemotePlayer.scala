package graphicgame

@remote trait RemotePlayer {
  def moveUpPressed(): Unit
  def moveUpReleased(): Unit
  def moveDownPressed(): Unit
  def moveDownReleased(): Unit
  def moveLeftPressed(): Unit
  def moveLeftReleased(): Unit
  def moveRightPressed(): Unit
  def moveRightReleased(): Unit
  def fireUpPressed(): Unit
  def fireUpReleased(): Unit
}
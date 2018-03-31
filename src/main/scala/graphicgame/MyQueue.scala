package graphicgame

trait MyQueue[A] {
  def enqueue(a: A): Unit
  def dequeue(): A
  def isEmpty: Boolean
  def peek: A
}
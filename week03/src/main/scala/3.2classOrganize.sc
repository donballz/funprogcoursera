import week3.Rational

val x = new Rational(1, 2)

trait Planar {
  def height: Int
  def width: Int
  def depth = height * width
}

def error(msg: String) = throw new Error(msg)

//error("Division by zero")

val i = null
val j: String = i

//val k: Int = i

val av = if (true) 1 else false



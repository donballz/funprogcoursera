class Rational(x: Int, y: Int) {
  require(y != 0, "denominator must be nonzero")

  def this(x: Int) = this(x, 1)

  def numer = x
  def denom = y

  def < (that: Rational) =
    numer * that.denom < that.numer * denom /* implied this */

  def max(that: Rational) =
    if (this < that) that
    else this

  def + (that: Rational) =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom)

  def unary_- = new Rational(-numer, denom)

  def - (that: Rational) =
    this + -that

  private def gcd(a: Int, b:Int): Int = if (b == 0) a else gcd(b, a % b)

  override def toString() = {
    val g = gcd(numer, denom)
    numer/g + "/" + denom/g
  }
}

val x = new Rational(1, 2)
x.numer
x.denom
val y = new Rational(2, 3)
x + y
y + x
-x

y - x

val i = new Rational(1, 3)
val j = new Rational(5, 7)
val k = new Rational(3, 2)

val gtest = new Rational(4, 6)


i - j - k

k.max(j)
j.max(k)


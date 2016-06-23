class Rational(x: Int, y: Int) {
  require(y != 0, "denominator must be nonzero")

  def this(x: Int) = this(x, 1)

  def numer = x
  def denom = y

  def less(that: Rational) =
    numer * that.denom < that.numer * denom /* implied this */

  def max(that: Rational) =
    if (less(that)) that
    else this

  def add(that: Rational) =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom)

  def neg = new Rational(-numer, denom)

  def sub(that: Rational) =
    add(that.neg)

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
x.add(y)
y.add(x)
x.neg

y.sub(x)

val i = new Rational(1, 3)
val j = new Rational(5, 7)
val k = new Rational(3, 2)

val gtest = new Rational(4, 6)


i.sub(j).sub(k)

k.max(j)
j.max(k)


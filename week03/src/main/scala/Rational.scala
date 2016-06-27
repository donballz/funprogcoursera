package week3
/**
  * Created by donald on 6/27/16.
  */
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

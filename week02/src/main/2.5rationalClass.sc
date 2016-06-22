class Rational(x: Int, y: Int) {
  def numer = x
  def denom = y

  def add(that: Rational) =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom)

  def neg() = new Rational(-numer, denom)

  def sub(that: Rational) =
    add(that.neg)

  override def toString() = numer + "/" + denom
}

val x = new Rational(1, 2)
x.numer
x.denom
val y = new Rational(2, 3)
x.add(y)
y.add(x)
x.neg()

y.sub(x)

val i = new Rational(1, 3)
val j = new Rational(5, 7)
val k = new Rational(3, 2)

i.sub(j).sub(k)


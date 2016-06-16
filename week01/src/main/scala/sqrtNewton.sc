def sqrt(x: Double) = {

  def abs(n: Double) = if (n > 0) n else -n

  def improve(guess: Double) =
    (x / guess + guess) / 2

  def isGoodEnough(guess: Double) =
    abs(guess * guess - x) / x < 0.00001

  def sqrtIter(guess: Double): Double =
    if (isGoodEnough(guess)) guess
    else sqrtIter(improve(guess))

  sqrtIter(1)
}

sqrt(2)
sqrt(4)
sqrt(1e-6)
sqrt(1e60)



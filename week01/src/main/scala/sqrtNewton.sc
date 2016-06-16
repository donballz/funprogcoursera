def abs(n: Double) = if (n > 0) n else -n

def improve(guess: Double, x: Double) =
  (x / guess + guess) / 2

def isGoodEnough(guess: Double, x: Double) =
  abs(guess * guess - x) / x < 0.00001

def sqrtIter(guess: Double, x: Double): Double =
  if (isGoodEnough(guess, x)) guess
  else sqrtIter(improve(guess, x), x)

def sqrt(x: Double) = sqrtIter(1, x)

sqrt(2)
sqrt(4)
sqrt(1e-6)
sqrt(1e60)



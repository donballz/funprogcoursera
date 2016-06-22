def product(f: Int => Int) (a: Int, b: Int): Int =
    if (a > b) 1
    else f(a) * product(f)(a + 1, b)


product({(x: Int) => x * x}) (1, 3)

def factorial(n: Int) = product(x => x)(1, n)

factorial(4)

def combined(f: Int => Int, g: (Int, Int) => Int, unit: Int) (a: Int, b: Int): Int =
  if (a > b) unit
  else g(f(a), combined(f, g, unit)(a + 1, b))

def sum(x: Int, y: Int) = combined(i => i, (j, k) => j + k, 0)(x, y)

sum(1, 10)




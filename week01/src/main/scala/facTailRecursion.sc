def factorial(n: Int): Int = {
  def pass_fac(m: Int, agg: Int): Int =
    if (m == 0) agg else pass_fac(m - 1, m * agg)

  pass_fac(n, 1)
}

factorial(4)

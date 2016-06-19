package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int =
    if (c == 0 || c == r) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)

  /**
    * Exercise 2
    */
  def balance(chars: List[Char]): Boolean = {
    def loop(open: Int, chars: List[Char]): Int =
      if (chars.isEmpty || open < 0) open
      else if (chars.head == '(') loop(open + 1, chars.tail)
      else if (chars.head == ')') loop(open - 1, chars.tail)
      else loop(open, chars.tail)

    loop(0, chars) == 0
  }
  /**
    * Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = {
    def checkOnePath(money: Int, coins: List[Int]): Int =
      if (coins.isEmpty || money < 0) 0
      else if (money == coins.head) 1
      else if (money > coins.head)
        checkOnePath(money - coins.head, coins) +
          checkOnePath(money - coins.head, coins.tail)
      else checkOnePath(money, coins.tail)

    def checkAll(count: Int, money: Int, coins: List[Int]): Int =
      if (coins.isEmpty) count
      else checkAll(count + checkOnePath(money, coins), money, coins.tail)

    checkAll(0, money, coins)
  }
}
def countChange(money: Int, coins: List[Int]): Int = {
  def checkOnePath(money: Int, coins: List[Int]): Int =
    if (coins.isEmpty || money < 0) 0
    else if (money == 0) 1
    else if (money >= coins.head)
      checkOnePath(money - coins.head, coins) +
      checkOnePath(money - coins.head, coins.tail)
    else checkOnePath(money, coins.tail)

  def checkAll(count: Int, money: Int, coins: List[Int]): Int =
    if (coins.isEmpty) count
    else checkAll(count + checkOnePath(money, coins), money, coins.tail)

  checkAll(0, money, coins)
}

countChange(4,List(1,2))

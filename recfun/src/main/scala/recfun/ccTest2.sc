def countChange(money: Int, coins: List[Int]): Int = {
  def innerLoop(money: Int, innerCoins: List[Int]): Int =
    if (innerCoins.isEmpty || money < 0) 0
    else if (money == 0) 1
    else innerLoop(money - innerCoins.head, innerCoins.tail)

  def outerLoop(money: Int, innerCoins: List[Int], acc: Int): Int =
    if (innerCoins.isEmpty) acc
    else outerLoop(money, innerCoins.tail, acc + innerLoop(money, coins))

  outerLoop(money, coins, 0)

}

countChange(4,List(1,2))
countChange(300,List(5,10,20,50,100,200,500)) /* 1022 */

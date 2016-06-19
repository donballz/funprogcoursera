def countChange(money: Int, coins: List[Int]): Int = {
  def checkOnePath(money: Int, start: Int, incoins: List[Int]): Int =
    if (cinoins.isEmpty || money < 0) 0
    else if (money == start)
      1
    else if (money > start)
      checkOnePath(money - start, incoins) +
        checkOnePath(money - start, incoins.tail)
    else checkOnePath(money, incoins.head, incoins.tail)

  def checkAll(count: Int, money: Int, incoins: List[Int]): Int =
    if (incoins.isEmpty) count
    else checkAll(count + checkOnePath(money, incoins.head, coins), money, incoins.tail)

  checkAll(0, money, coins)
}

countChange(4,List(1,2))
countChange(300,List(5,10,20,50,100,200,500)) /* 1022 */

def countChange(money: Int, coins: List[Int]): Int = {
  def myLoop(money: Int, coins: List[Int], acc: Int): Int =
    if (coins.isEmpty || money < 0) 0
    else if (money == 0)
      acc + 1
    else
      myLoop(money - coins.head, coins, acc) +
        myLoop(money - coins.head, coins.tail, acc)

  myLoop(money, coins, 0)
}

countChange(4,List(1,2))
countChange(300,List(5,10,20,50,100,200,500)) /* 1022 */

def countChange(money: Int, coins: List[Int]): Int = {
  def iter(coins: List[Int]): List[Int] =
    if (coins.isEmpty)


  checkAll(0, money, coins)
}

countChange(4,List(1,2))
countChange(300,List(5,10,20,50,100,200,500)) /* 1022 */

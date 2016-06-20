# coins.isEmpty = not coins.any?
# coins.head = coins[0]
# coins.tail = coins[1..-1]

DEBUG = false
COINS = [1,2,3]

def innerLoop(money, innerCoins)
    if (not innerCoins.any?) or money < 0 
    	puts "path: #{track}; term at 0 for lack of coins" if not innerCoins.any? and DEBUG
    	puts "path: #{track}; term at 0 for lack of money" if money < 0 and DEBUG
    	0
    elsif (money == 0) 
    	1
    else 
    	innerLoop(money - innerCoins[0], innerCoins[1..-1])
    end
end

def outerLoop(money, innerCoins, acc)
    if (not innerCoins.any?) 
    	acc
    else 
    	outerLoop(money, innerCoins[1..-1], acc + innerLoop(money, COINS))
    end
end

def countChange(money, coins)
  outerLoop(money, coins, 0)
end

#puts countChange(4, [1,2]) # 3
puts countChange(4, COINS) # 4
#puts countChange(300,[5,10,20,50,100,200,500]) # 1022
#puts countChange(300,[500,5,50,100,20,200,10]) # 1022
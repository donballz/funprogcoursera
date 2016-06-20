# coins.isEmpty = not coins.any?
# coins.head = coins[0]
# coins.tail = coins[1..-1]

DEBUG = true
COINS = [1,2]

def myloop(money, innerCoins, acc, track)
	puts "#{track}" if DEBUG
	puts "money: #{money}; coin: #{innerCoins[0]}" if DEBUG
    if (not innerCoins.any?) or money < 0 
    	puts "term at 0 for lack of coins" if (not innerCoins.any?) and DEBUG
    	puts "term at 0 for lack of money" if money < 0 and DEBUG
    	0
    elsif (money == 0) 
    	puts "term at 1" if DEBUG
    	acc + 1
    else 
    	track.push({money => innerCoins[0]})
    	myloop(money - innerCoins[0], innerCoins[1..-1], acc, track.reverse.reverse) +
    	myloop(money - innerCoins[0], innerCoins, acc, track.reverse.reverse)
    end
end

def countChange(money, coins)
  myloop(money, coins, 0, [])
end

puts countChange(4, [1,2]) # 3
#puts countChange(4, COINS) # 4
#puts countChange(300,[5,10,20,50,100,200,500]) # 1022
#puts countChange(300,[500,5,50,100,20,200,10]) # 1022
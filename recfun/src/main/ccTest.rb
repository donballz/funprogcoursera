# coins.isEmpty = not coins.any?
# coins.head = coins[0]
# coins.tail = coins[1..-1]

def checkOnePath(money, coins, track)
    if not coins.any? or money < 0
    	puts "path: #{track}; term at 0 for lack of coins" unless coins.any?
    	puts "path: #{track}; term at 0 for lack of money" if money < 0
    	0
    elsif (money == 0) 
    	puts "path: #{track}; term at 1"
    	1
    elsif (money >= coins[0])
    	track.push(coins[0])
      	checkOnePath(money - coins[0], coins, track) +
      	checkOnePath(money - coins[0], coins[1..-1], track)
    else 
    	checkOnePath(money, coins[1..-1], track)
    end
end
    
def checkAll(count, money, coins)
    if not coins.any?
    	count
    else 
    	checkAll(count + checkOnePath(money, coins, []), money, coins[1..-1])
    end
end
    
def countChange(money, coins)
  checkAll(0, money, coins)
end

puts countChange(4, [1,2])

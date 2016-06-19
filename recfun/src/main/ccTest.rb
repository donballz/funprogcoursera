# coins.isEmpty = not coins.any?
# coins.head = coins[0]
# coins.tail = coins[1..-1]

DEBUG = true

def checkOnePath(money, coins, track, path)
	puts path if DEBUG
    if (not coins.any?) or money < 0
    	puts "path: #{track}; term at 0 for lack of coins" if not coins.any? and DEBUG
    	puts "path: #{track}; term at 0 for lack of money" if money < 0 and DEBUG
    	0
    elsif (money == coins[0]) 
    	track.push(coins[0])
    	puts "path: #{track}; term at 1 with coins #{coins}" if DEBUG
    	1 
    elsif (money > coins[0])
    	track.push(coins[0])
    	puts "track add #{coins[0]} with money #{money} for result #{track}" if DEBUG
      	checkOnePath(money - coins[0], coins, track.reverse.reverse, path + "A") +
      	checkOnePath(money - coins[0], coins[1..-1], track.reverse.reverse, path + "B")
    else 
    	puts "coin #{coins[0]} too large for money #{money} #{track}" if DEBUG
    	checkOnePath(money, coins[1..-1], track.reverse.reverse, path + "C")
    end
end
    
def checkAll(count, money, coins)
    if not coins.any?
    	count
    else 
    	checkAll(count + checkOnePath(money, coins, [], "#{coins[0]}"), money, coins[1..-1])
    end
end
    
def countChange(money, coins)
  checkAll(0, money, coins)
end

#puts countChange(4, [1,2]) # 3
puts countChange(4, [1,2,3]) # 4
#puts countChange(300,[5,10,20,50,100,200,500]) # 1022
#puts countChange(300,[500,5,50,100,20,200,10]) # 1022

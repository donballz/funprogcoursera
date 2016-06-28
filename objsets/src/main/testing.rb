

# A class to represent tweets.
class Tweet
	ATTRS = [:user, :text, :retweets]
	attr_reader(*ATTRS)
	
	def initialize(user, text, retweets) 
		@user = user
		@text = text
		@retweets = retweets
	end
  	
  	def to_s
    	"User: " + user + "\n" +
    	"Text: " + text + " [" + retweets.to_s + "]"
	end
end
 # This represents a set of objects of type `Tweet` in the form of a binary search
 # tree. Every branch in the tree has two children (two `TweetSet`s). There is an
 # invariant which always holds: for every branch `b`, all elements in the left
 # subtree are smaller than the tweet at `b`. The elements in the right subtree are
 # larger.
 #
 # Note that the above structure requires us to be able to compare two tweets (we
 # need to be able to say which of two tweets is larger, or if they are equal). In
 # this implementation, the equality / order of tweets is based on the tweet's text
 # (see `def incl`). Hence, a `TweetSet` could not contain two tweets with the same
 # text from different users.
 #
 #
 # The advantage of representing sets as binary search trees is that the elements
 # of the set can be found quickly. If you want to learn more you can take a look
 # at the Wikipedia page [1], but this is not necessary in order to solve this
 # assignment.
 #
 # [1] http://en.wikipedia.org/wiki/Binary_search_tree
 
class TweetSet 

  
   # This method takes a predicate and returns a subset of all the elements
   # in the original set for which the predicate is true.
   #
   # Question: Can we implement this method here, or should it remain abstract
   # and be implemented in the subclasses?

	def filter(q) 
		filterAcc(q, Empty.new)
	end
  
   # This is a helper method for `filter` that propagetes the accumulated tweets.
   
  	#def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet

  
   # Returns a new `TweetSet` that is the union of `TweetSet`s `this` and `that`.
   #
   # Question: Should we implement this method here, or should it remain abstract
   # and be implemented in the subclasses?
   
    #def union(that: TweetSet): TweetSet
  
  
   # Returns the tweet from this set which has the greatest retweet count.
   #
   # Calling `mostRetweeted` on an empty set should throw an exception of
   # type `java.util.NoSuchElementException`.
   #
   # Question: Should we implement this method here, or should it remain abstract
   # and be implemented in the subclasses?
   
    #def mostRetweeted: Tweet
  
  
   # Returns a list containing all tweets of this set, sorted by retweet count
   # in descending order. In other words, the head of the resulting list should
   # have the highest retweet count.
   #
   # Hint: the method `remove` on TweetSet will be very useful.
   # Question: Should we implement this method here, or should it remain abstract
   # and be implemented in the subclasses?
   
    #def descendingByRetweet: TweetList = ???

    #def isEmpty: Boolean
  
  
   # The following methods are already implemented
   

  
   # Returns a new `TweetSet` which contains all elements of this set, and the
   # the new element `tweet` in case it does not already exist in this set.
   #
   # If `this.contains(tweet)`, the current set is returned.
   
  #def incl(tweet: Tweet): TweetSet

  
   # Returns a new `TweetSet` which excludes `tweet`.
   
  #def remove(tweet: Tweet): TweetSet

  
   # Tests if `tweet` exists in this `TweetSet`.
   
  #def contains(tweet: Tweet): Boolean

  
   # This method takes a function and applies it to every element in the set.
   
  #def foreach(f: Tweet => Unit): Unit
end

class Empty < TweetSet 
	def initialize
		@elem = nil
	end
	
	def to_s
		'.'
	end
  	
  	def isEmpty 
  		true
  	end
  	
  	def union(that)
  		that
  	end
  	
  	def filterAcc(q, acc)
  		acc
  	end
  	
  	def mostRetweeted
  		raise 'NoSuchElementException("Empty tweetset")'
  	end
  
   # The following methods are already implemented

  	def contains
  		false
	end
	
  	def incl(tweet) 
  		NonEmpty.new(tweet, Empty.new, Empty.new)
	end
	
  	def remove(tweet)
  		self
  	end

  	def foreach(f)
  		self
  	end
end

class NonEmpty < TweetSet 
	ATTRS = [:elem, :left, :right]
	attr_reader(*ATTRS)
	
	def initialize(elem, left, right)
		@elem = elem
		@left = left
		@right = right
	end
	
	def to_s 
		"{" + @left.to_s + @elem.to_s + @right.to_s + "}"
	end
	
  	def isEmpty 
  		false
  	end

  	def union(that)
    	return @left.union(@right).union(that).incl(@elem)
    end

  	def filterAcc(q, acc)
    	nex = @left.filterAcc(q, acc).union(@right.filterAcc(q, acc))
    	if send(q, @elem) 
    		acc.union(nex.union(NonEmpty.new(@elem, Empty.new, Empty.new)))
    	else 
    		nex
    	end
	end
	
	private 
	
	def max(x, y) 
		if (x.retweets > y.retweets) 
			x 
		else 
			y
		end
	end
	
	def helper(branch, winner)
      	if branch.isEmpty 
      		winner
      	elsif (branch.elem.retweets > winner.retweets) 
      		max(helper(branch.left, branch.elem), helper(branch.right, branch.elem))
      	else 
      		max(helper(branch.left, winner), helper(branch.right, winner))
      	end
    end
    
    public
		
  	def mostRetweeted
    	helper(self, Tweet.new("a", "at", -1))
  	end
  
   # The following methods are already implemented
   
  	def contains(x)
    	if (x.text < elem.text) 
    		@left.contains(x)
    	elsif (@elem.text < x.text) 
    		@right.contains(x)
    	else 
    		true
    	end
	end
	
  	def incl(x)
    	if (x.text < @elem.text) 
    		NonEmpty.new(@elem, @left.incl(x), @right)
    	elsif (elem.text < x.text) 
    		NonEmpty.new(@elem, @left, @right.incl(x))
    	else 
    		self
    	end
  	end

  	def remove(tw)
    	if (tw.text < @elem.text) 
    		NonEmpty.new(@elem, @left.remove(tw), @right)
    	elsif (@elem.text < tw.text) 
    		NonEmpty.new(@elem, @left, @right.remove(tw))
    	else 
    		@left.union(@right)
    	end
    end

  	def foreach(f)
    	f(elem)
    	left.foreach(f)
    	right.foreach(f)
  	end
end

set1 = Empty.new
set2 = set1.incl(Tweet.new("a", "a body", 20))
set3 = set2.incl(Tweet.new("b", "b body", 20))
c = Tweet.new("c", "c body", 7)
d = Tweet.new("d", "d body", 9)
set4a = set1.incl(c).incl(d)
set4c = set3.incl(c)
set4d = set3.incl(d)
set5 = set4c.incl(d)

def q(tweet)
	tweet.user == 'a'
end

def k(tweet)
	tweet.retweets == 20
end

#puts set1.filter(:q)
#puts set5.filter(:q)
#puts set5.filter(:k)
#puts set4c.union(set4d)
#puts set1.union(set5)
#puts set5.union(set1)
puts set4a.mostRetweeted.retweets
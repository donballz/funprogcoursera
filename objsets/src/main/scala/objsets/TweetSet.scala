package objsets

import TweetReader._

/**
 * A class to represent tweets.
 */
class Tweet(val user: String, val text: String, val retweets: Int) {
  override def toString: String =
    "User: " + user + "\n" +
    "Text: " + text + " [" + retweets + "]"
}

abstract class TweetSet {

    def filter(p: Tweet => Boolean): TweetSet = filterAcc(p, new Empty)

    def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet

    def union(that: TweetSet): TweetSet

    def mostRetweeted: Tweet

    def descendingByRetweet: TweetList =
      if (isEmpty) Nil
      else new Cons(this.mostRetweeted, this.remove(this.mostRetweeted).descendingByRetweet)

    def isEmpty: Boolean

    def helper(winner: Tweet): Tweet

    def incl(tweet: Tweet): TweetSet

    def remove(tweet: Tweet): TweetSet

    def contains(tweet: Tweet): Boolean

    def foreach(f: Tweet => Unit): Unit
}

class Empty extends TweetSet {
  def isEmpty = true
  override def toString = "."
  def union(that: TweetSet): TweetSet = that
  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet = acc
  def mostRetweeted: Tweet = throw new NoSuchElementException("Empty tweetset")
  def helper(winner: Tweet) = winner
  /**
   * The following methods are already implemented
   */

  def contains(tweet: Tweet): Boolean = false

  def incl(tweet: Tweet): TweetSet = new NonEmpty(tweet, new Empty, new Empty)

  def remove(tweet: Tweet): TweetSet = this

  def foreach(f: Tweet => Unit): Unit = ()
}

class NonEmpty(elem: Tweet, left: TweetSet, right: TweetSet) extends TweetSet {
  def isEmpty = false

  override def toString = "{" + left + elem + right + "}"

  def union(that: TweetSet): TweetSet =
    (left union (right union that)) incl elem

  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet = {
    val next = left.filterAcc(p, acc) union right.filterAcc(p, acc)
    if (p(elem)) acc union next union new NonEmpty(elem, new Empty, new Empty)
    else next
  }

  def helper(winner: Tweet): Tweet = {
    def max(x: Tweet, y: Tweet) = if (x.retweets > y.retweets) x else y

    if (isEmpty) winner
    else if (elem.retweets > winner.retweets) max(left.helper(elem), right.helper(elem))
    else max(left.helper(winner), right.helper(winner))
  }

  def mostRetweeted: Tweet =
    this.helper(elem)

  /**
   * The following methods are already implemented
   */

  def contains(x: Tweet): Boolean =
    if (x.text < elem.text) left.contains(x)
    else if (elem.text < x.text) right.contains(x)
    else true

  def incl(x: Tweet): TweetSet = {
    if (x.text < elem.text) new NonEmpty(elem, left.incl(x), right)
    else if (elem.text < x.text) new NonEmpty(elem, left, right.incl(x))
    else this
  }

  def remove(tw: Tweet): TweetSet =
    if (tw.text < elem.text) new NonEmpty(elem, left.remove(tw), right)
    else if (elem.text < tw.text) new NonEmpty(elem, left, right.remove(tw))
    else left.union(right)

  def foreach(f: Tweet => Unit): Unit = {
    f(elem)
    left.foreach(f)
    right.foreach(f)
  }
}

trait TweetList {
  def head: Tweet
  def tail: TweetList
  def isEmpty: Boolean
  def foreach(f: Tweet => Unit): Unit =
    if (!isEmpty) {
      f(head)
      tail.foreach(f)
    }
}

object Nil extends TweetList {
  def head = throw new java.util.NoSuchElementException("head of EmptyList")
  def tail = throw new java.util.NoSuchElementException("tail of EmptyList")
  def isEmpty = true
}

class Cons(val head: Tweet, val tail: TweetList) extends TweetList {
  def isEmpty = false
}


object GoogleVsApple {
  val google = List("android", "Android", "galaxy", "Galaxy", "nexus", "Nexus")
  val apple = List("ios", "iOS", "iphone", "iPhone", "ipad", "iPad")

  def assoc(words: List[String]): (Tweet) => Boolean =
    (tw: Tweet) => words.exists((word: String) => tw.text.contains(word))

  lazy val googleTweets: TweetSet = allTweets.filter(assoc(google))
  lazy val appleTweets: TweetSet = allTweets.filter(assoc(apple))
  
  /**
   * A list of all tweets mentioning a keyword from either apple or google,
   * sorted by the number of retweets.
   */
  lazy val trending: TweetList = (googleTweets union appleTweets).descendingByRetweet
  }

object Main extends App {
  // Print the trending tweets
  GoogleVsApple.trending foreach println
}

abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  def incl(x: Int): IntSet =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this

  def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true

  def union(other: IntSet): IntSet =
    ((left union right) union other) incl elem

  override def toString = "{" + left + elem + right + "}"
}

object Empty extends IntSet {
  def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
  def contains(x: Int): Boolean = false
  def union(other: IntSet): IntSet = other
  override def toString = "."
}

val t1 = new NonEmpty(3, Empty, Empty)
val t2 = t1 incl 6
val t3 = new NonEmpty(5, Empty, Empty)
val t4 = t3 incl 4
val t5 = t2 union t4

val test1 = t2 contains 5
val test2 = t2 contains 3

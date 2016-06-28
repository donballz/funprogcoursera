import objsets._

val set1 = new Empty
val set2 = set1.incl(new Tweet("a", "a body", 20))
val set3 = set2.incl(new Tweet("b", "b body", 20))
val c = new Tweet("c", "c body", 7)
val d = new Tweet("d", "d body", 9)
val set4a = set1 incl c incl d
val set4c = set3.incl(c)
val set4d = set3.incl(d)
val set5 = set4c.incl(d)

set1.isEmpty

set5.isEmpty

set4a.mostRetweeted

set5.mostRetweeted

set5.remove(d)

def range(x: Int): TweetList =
  if (x < 0) Nil
  else new Cons(new Tweet("a", "#watwat", x), range(x - 1))

range(10)
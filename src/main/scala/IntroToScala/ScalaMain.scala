package IntroToScala

object Example1 extends App {
  override def main(args: Array[String]): Unit = {
    println("Hello, Scala!")
  }
}

// extends App is optional
object Example2 {
  def main(args: Array[String]): Unit = {
    println("Hello, Scala!")
  }
}

// Mutable members, ie x and y can be set after class initiatialization
class ScalaClassMutable(var x: Int, var y: Int)

object ScalaClassMutableMain {
  def main(args: Array[String]): Unit = {
    var scm = new ScalaClassMutable(5, 10)
    var result = scm.x + scm.y
    println(result)

    scm.x = 1
    scm.y = 2

    result = scm.x + scm.y
    println(result)
  }
}

// Immutable members, ie x and y cannot be changed after class initialization
class ScalaClassImmutable(val x: Int, val y: Int) {
  def sum = x + y
}

// TAKEAWAY: use "val" not "var," immutable classes not mutable classes
object ScalaClassImmutableMain {
  def main(args: Array[String]): Unit = {
    // NOTE: "val" means it cannot be changed...
    val sci = new ScalaClassImmutable(5, 10)
    println(sci.sum)

    // ...so we just create a new instance
    val sciTwo = new ScalaClassImmutable(1, 2)
    println(sciTwo.sum)
  }
}


package IntroToScala

object ScalaForLoop {
  def main(args: Array[String]): Unit = {

    val array: Array[Int] = Array[Int](0, 1, 2, 3, 4)

    // Bad example! (why?)
    // Equivalent to: for (int i; i = 0; i++)
    var i: Int = 0
    while (i < array.length) {
      println(array(i))
      i = i + 1
    }

    /*
    // Better
    for (i <- 0 until array.length) {
      println(array(i))
    }

    // Even better
    for (i <- array) {
      println(i)
    }

    // Best, idiomatic scala
    array.foreach( i => println())

    array.foreach(println(_))

    array.foreach(println)

    array.foreach(println)

    println(array.mkString)

    println(array.mkString(","))
    */
  }
}
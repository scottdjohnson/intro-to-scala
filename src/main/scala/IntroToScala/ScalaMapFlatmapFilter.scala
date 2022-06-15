package IntroToScala

object ScalaMapFlatmapFilter {

  def main(args: Array[String]): Unit = {
    val list: List[Int] = List(0, 1, 2, 3, 5)

    // Increment each value in the list
    println(list.map(_ + 1))

    /*
    // Return a list with only the even number

    // map and flatten
    val evenList1: List[Option[Int]] = list.map { i => if (i % 2 == 0) Some(i) else None }

    println(evenList1) // Good, but not done yet, note the return type

    val evenList2: List[Int] = evenList1.flatten

    println(evenList2)

    // flatmap
    val evenList3: List[Int] = list.flatMap{ i => if (i % 2 == 0) Some(i) else None }

    println(evenList3)

    // filter
    val evenList4: List[Int] = list.filter(_ % 2 == 0)

    println(evenList4)

    var array = Array[Int](0, 1, 2, 3)
    */
  }
}
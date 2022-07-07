package IntroToScala

/**
  * Given an array of integers, assign another array the same contents with each value incrememnted
 */
object ScalaMapMain {
  def main(args: Array[String]): Unit = {

    val array: Array[Int] = Array[Int](0, 1, 2, 3, 4)
    println(s"Original array:  ${array.mkString(", ")}")

    // What if we want to return the array as a modified array (eg increment each value)
/*
    // Bad solution - create a new array, and fill it with the modified values
    val arrayAltered = new Array[Int](array.length)

    for (i <- 0 until array.length) {
      arrayAltered(i) = array(i) + 1
    }

    println(s"Modified arrray: ${arrayAltered.mkString(", ")}")


    // Better, idiomatic Scala
    val list: List[Int] = List[Int](0, 1, 2, 3, 4)
    val listAltered1: List[Int] = list.map { x => x + 1}
    println(listAltered1)

    // What is the difference between map and foreach?

    // Same but simpler
    val listAltered2: List[Int] = list.map { _ + 1}
    println(listAltered2)

    // Can also use mkString
    println(listAltered2.mkString)

    // What are the values of result1 and result2?
    val myOptInt: Option[Int] = Some(2)
    val result1 = myOptInt.map(i => i * i)
    println(s"result1: $result1")

    val myNoneInt: Option[Int] = None
    val result2 = myNoneInt.map(i => i * i)
    println(s"result2: $result2")

    val list3 = listAltered2
    // What is wrong with a unit test like this?
    list3.foreach { i =>
      assert(i > 0)
    }

*/
  }
}
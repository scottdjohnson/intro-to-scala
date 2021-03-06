package IntroToScala

/*
  A Scala Option[A] as class whose instance is either Some(s: A) or None, where "s" is the raw value
*/
class ScalaTuple(val x: Option[Int], val y: Option[Int])

object ScalaOption {

  // Option[A]
  //  Some[A](value)
  //  None

  // Function returns an int, but sometimes it returns a null
  //  eg reads a file that has a value (but maybe the file is not there or it is empty)
  def getOptionIntFromSomewhere(): Option[Int] =
    if (scala.util.Random.nextBoolean())
      None
    else
      Some(scala.util.Random.nextInt(1000))

  def main(arg: Array[String]): Unit = {

    val myOptionInt: Option[Int] = getOptionIntFromSomewhere()

    // Why is this better than the Java example?
    myOptionInt match {
      case Some(i) =>
        println("Hello user, you have $" + i)
      case None =>
        println("Hello user, it looks like you are not in the system")
    }
/*

    myOptionInt.foreach(i => println(i))

    val newInt =  myOptionInt.map(i => i + 1)
    println(newInt)


    // What are the values of result1 and result2?
    val myOptInt: Option[Int] = Some(2)
    val result1 = myOptInt.map(i => i * i)
    println(s"result1: $result1")



    val myNoneInt: Option[Int] = None
    val result2 = myNoneInt.map(i => i * i)
    println(s"result2: $result2")



    val listFull = List(0, 1, 2)
    listFull.headOption.foreach(println)

    val listEmpty = List()
    listEmpty.headOption.foreach(println)





    val wealth: Int = getOptionIntFromSomewhere().getOrElse(0)
    println(s"Hello user, you have $wealth dollars")

    // anti-pattern - why?
    val wealth2: Int = getOptionIntFromSomewhere().get
    println(s"Hello user, you have $wealth2 dollars")

    // anti-pattern - why?
    val wealth3 = getOptionIntFromSomewhere()
    if (wealth3.isEmpty)
      println("Hello user, it looks like you are not in the system")
    else
      println("Hello user, you have $" + wealth3.get)
*/
  }
}



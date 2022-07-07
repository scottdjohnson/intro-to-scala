package IntroToScala

object ScalaFunction {
  def main(args: Array[String]) = {
    println(add(1, 2))
    //println(operation(1, 2, (i, j) => i + j))
  }

  def add(x: Int, y: Int): Int = x + y

  def operation(x: Int, y: Int, op: (Int, Int) => Int): Int = op(x, y)
}
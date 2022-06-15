package IntroToScala

import org.apache.logging.log4j.{Level, LogManager}
import org.apache.logging.log4j.core.config.Configurator

import scala.util.{Failure, Success, Try}

class UnstableFunctionException(s: String) extends Exception(s)

/**
  * Given an array of integers, assign another array the same contents with each value incrememnted
  */
object ScalaException {

  Configurator.setRootLevel(Level.INFO)
  val logger = LogManager.getLogger()

  def main(args: Array[String]): Unit = {
    testExample()
    //testGood()
    //testMaybeGood()
  }

  // Return a random int or an exception
  // eg an HTTP client, which throws an exception when the server is down
  def unstableFunction(): Int =
    if (scala.util.Random.nextBoolean())
      throw new UnstableFunctionException("EXCEPTION")
    else
      scala.util.Random.nextInt()

  def logEvenOdd(i: Int) = if (i % 2 == 0) logger.info(s"$i is even") else logger.info(s"$i is odd")

  def testExample(): Unit =
    try {
      logEvenOdd(unstableFunction())
    } catch {
      case e: UnstableFunctionException =>
        logger.error("This is an unstable function and it failed!", e) // Do not throw away the exception!
      case e: Exception =>
        logger.error("Some exception occurred", e) // Never throw away the exception!
    }


  // Turn your unstable function into a stable function
  def stableFunction(): Try[Int] = Try { unstableFunction() }

  def testGood(): Unit =
    stableFunction() match {
      case Success(s) => logEvenOdd(s)
      case Failure(e: UnstableFunctionException) => logger.error("This is an unstable function and it failed!", e) // Do not throw away the exception!
      case Failure(e: Exception) => logger.error("Some exception occurred", e) // Do not throw away the exception!
    }


  // Good, will work sometimes, but what is missing?
  def testMaybeGood(): Unit = stableFunction().map(logEvenOdd(_))

  /*
    This won't even compile - why???

    def testSyntaxError() = logEvenOdd(stableFunction())
  */

}
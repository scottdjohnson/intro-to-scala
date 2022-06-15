package IntroToScala

import org.apache.logging.log4j.core.config.Configurator
import org.apache.logging.log4j.{Level, LogManager}

import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object ScalaFuture {

  Configurator.setRootLevel(Level.INFO)
  val logger = LogManager.getLogger()

  // Return a random int or throw an exception
  // eg an HTTP client, which throws an exception when the server is down, or sometimes hangs a few seconds before completing
  def unstableFunction(): Int =
    if (scala.util.Random.nextBoolean())
      throw new UnstableFunctionException("EXCEPTION")
    else {
      Thread.sleep(scala.util.Random.nextInt(5) * 1000) // sleep for up to 5 seconds
      scala.util.Random.nextInt()
    }

  // This function takes a non-deterministic amount of time, eg an http client call
  //  and sometimes fails
  def intWithWait(): Future[Int] = Future {  unstableFunction() }

  def main(args: Array[String]) = {

    logger.info("Starting f1")
    val f1 = intWithWait()
    logger.info("Starting f2")
    val f2 = intWithWait()
    logger.info("Starting f3")
    val f3 = intWithWait()

    f1.onComplete{
      case Success(i) =>
        logger.info(s"f1 completed with value: $i")
      case Failure(ex) =>
        logger.error(s"Failed call", ex)
    }

    f2.onComplete{
      case Success(i) =>
        logger.info(s"f2 completed with value: $i")
      case Failure(ex) =>
        logger.error(s"Failed call", ex)
    }

    f3.onComplete{
      case Success(i) =>
        logger.info(s"f3 completed with value: $i")
      case Failure(ex) =>
        logger.error(s"Failed call", ex)
    }



    //Thread.sleep(5000)


    val result = for {
      v1 <- f1
      v2 <- f2
      v3 <- f3
    } yield v1 + v2 + v3

    result onComplete  {
      case Success(v) =>
        logger.info(s"Result: $v")
      case Failure (ex) =>
        logger.error(s"Failed future", ex)
    }

    // Usually you return a Future directly to a lib or framework, eg Akka or Play, which will wait and handle
    //  the response appropriately.
    // Usually you do NOT want to Await for a Future, except in a unit test
    logger.info(Await.ready(result, Duration.Inf))

  }
}
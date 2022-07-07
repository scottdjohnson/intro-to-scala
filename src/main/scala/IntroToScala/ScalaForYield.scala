package IntroToScala

import org.apache.logging.log4j.{Level, LogManager}
import org.apache.logging.log4j.core.config.Configurator

import scala.util.{Failure, Success, Try}

object ScalaForYield {

  Configurator.setRootLevel(Level.INFO)
  val logger = LogManager.getLogger()

  val a: Option[Int] = Some(1)
  val b: Option[Int] = Some(2)
  val c: Option[Int] = Some(3)

  // Note these are all members of the ScalaForYield
  val t1 = Try[Option[Int]]{Some(1)}
  val t2 = Try[Option[Int]]{Some(2)}
  val t3 = Try[Option[Int]]{Some(1)}
  val t4 = Try[Option[Int]]{Some(3)}

  def main(args: Array[String]) = {
    println(additionMatch(a, b, c))
    println(additionMap(a, b, c))
    println(additionFlatMap(a, b, c))
    println(additionForYield(a, b, c))

    println(tryOptionResult(t1, t2, t3, t4))
    //println(tryOptionResult2(t1, t2, t3, t4))
    //println(tryOptionForYield(t1, t2, t3, t4))
    //println(resolveTryOption(tryOptionForYield(t1, t2, t3, t4)))
    //println(tryOptionGetOrElse(t1, t2, t3, t4))
    //println(antipattern(t1, t2, t3, t4))
    //println(simplifiedForYield(t1, t2, t3, t4))
  }

  def additionMatch(a: Option[Int], b: Option[Int], c: Option[Int]): Int =
    a match {
      case Some(v1) =>
        b match {
          case Some(v2) =>
            c match {
              case Some(v3) => v1 + v2 + v3
              case None => 0
            }
          case None => 0
        }
      case None => 0
    }


  // What type does this return?
  def additionMap(a: Option[Int], b: Option[Int], c: Option[Int]) =
    a.map( v1 => b.map ( v2 => c.map ( v3 => v1 + v2 + v3)))


  def additionFlatMap(a: Option[Int], b: Option[Int], c: Option[Int]) =
    a.flatMap{ v1 =>
      b.flatMap {
        v2 => c.map ( v3 => v1 + v2 + v3)
      }
    }

  val d = Some(4)

  // Requirements of for/yield
  //  Everything in the for block must be the same type
  //  This type must have a map and a flatMap defined
  def additionForYield(a: Option[Int], b: Option[Int], c: Option[Int]): Option[Int] =
    for {
      v1 <- a
      v2 <- b
      v3 <- c
      v4 <- d
    } yield {
      v1 + v2 + v3 + v4
    }


  def tryOptionResult(t1: Try[Option[Int]], t2: Try[Option[Int]], t3: Try[Option[Int]], t4: Try[Option[Int]]) =  t1.map { o1 => o1.map {v1 =>
    t2.map { o2 => o2.map { v2 =>
      t3.map { o3 => o3.map { v3 =>
        t4.map { o4 => o4.map { v4 => v1 + v2 + v3 + v4}}
      }}}}}}



  // Complete answer, but very tedious
  def tryOptionResult2(t1: Try[Option[Int]], t2: Try[Option[Int]], t3: Try[Option[Int]], t4: Try[Option[Int]]): Int =
    t1 match {
      case Success(o1) => o1 match {
          case Some(v1) =>
            t2 match {
            case Success(o2) => o2 match {
              case Some(v2) =>
                t3 match {
                  case Success(o3) => o3 match {
                    case Some(v3) =>
                      t4 match {
                        case Success(o4) => o4 match {
                          case Some(v4) => v1 + v2 + v3 + v4
                          case None => 0
                        }
                        case Failure(ex) => logger.info("Failed to retrieve value", ex)
                          0
                      }
                    case None => 0
                  }
                  case Failure(ex) =>
                    logger.info("Failed to retrieve value", ex)
                    0
                }
              case None => 0
            }
            case Failure(ex) =>
              logger.info("Failed to retrieve value", ex)
              0
          }
          case None => 0
        }
      case Failure(ex) =>
        logger.info("Failed to retrieve value", ex)
        0
    }

  // Just as good in almost all cases, much more manageable
  def tryOptionForYield(t1: Try[Option[Int]], t2: Try[Option[Int]], t3: Try[Option[Int]], t4: Try[Option[Int]]): Try[Option[Int]] =
    for {
      o1 <- t1
      o2 <- t2
      o3 <- t3
      o4 <- t4
    } yield {
      for {
        v1 <- o1
        v2 <- o2
        v3 <- o3
        v4 <- o4
      } yield v1 + v2 + v3 + v4
    }

  // What can we do with this?

  // 1) Resolve to final int value
  def resolveTryOption(tryOpt: Try[Option[Int]]): Int =
    tryOpt match {
      case Success(t) =>
        t match {
          case Some(o) => o
          case None => 0
      }
      case Failure(ex) =>
        logger.info("Failed to retrieve value", ex)
        0
    }

  // 2) Simplifiy with getrOrElse, although we do not have any intermediate logging
  def tryOptionGetOrElse(t1: Try[Option[Int]], t2: Try[Option[Int]], t3: Try[Option[Int]], t4: Try[Option[Int]]): Int =
    t1.getOrElse(None).getOrElse(0) + t2.getOrElse(None).getOrElse(0) + t3.getOrElse(None).getOrElse(0) + t4.getOrElse(None).getOrElse(0)

  // antipattern - DO NOT DO THIS!!!
  def antipattern(t1: Try[Option[Int]], t2: Try[Option[Int]], t3: Try[Option[Int]], t4: Try[Option[Int]]): Int =
    t1.get.get + t2.get.get + t3.get.get + t4.get.get

  // Use toOption and flatten
  // Start with Try[Option[Int]]
  //  toOption -> Option[Option[Int]]
  //  flatten -> Option[Int]
  def simplifiedForYield(t1: Try[Option[Int]], t2: Try[Option[Int]], t3: Try[Option[Int]], t4: Try[Option[Int]]): Option[Int] =
    for {
      v1 <- t1.toOption.flatten
      v2 <- t2.toOption.flatten
      v3 <- t3.toOption.flatten
      v4 <- t4.toOption.flatten
    } yield v1 + v2 + v3 + v4
}
package org.x4444.onakka

import akka.actor.{Actor, Props}
import akka.event.Logging
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

/**
  * Dispatcher
  */
class DispActor extends Actor {
  val log = Logging(context.system, this)

  val myActor = context.actorOf(Props[MyActor], "myactor")

  implicit val timeout = Timeout(5 seconds)

  override def receive: Receive = {
    case "test" =>
      val f1 = myActor ? "test"
      val f2 = myActor ? "test2"
      val f3 = myActor ? "test3"
      val r1 = Await.result(f1, timeout.duration).asInstanceOf[String]
      val r2 = Await.result(f2, timeout.duration).asInstanceOf[String]
      val r3 = Await.result(f3, timeout.duration).asInstanceOf[String]
      val res = (r1, r2, r3)
      log.info(s"collected all $res")
      sender ! res
    case "stop" => log.info(s"received stop msg")
      context.stop(self)
    case "kill" => log.info(s"received Kill msg")
      context.system.terminate()
    case x: Any => log.info(s"received $x")
  }
}

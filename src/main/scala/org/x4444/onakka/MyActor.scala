package org.x4444.onakka

import akka.actor.Actor
import akka.event.Logging
import org.x4444.onakka.MyActor._

/**
  * MyActor
  */
class MyActor extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case "test" => log.info("received test")
      sender ! "OK"
    case "test2" => log.info("received test2")
      Thread.sleep(500)
      sender ! "OK2"
    case "test3" => log.info("received test3")
      Thread.sleep(600)
      sender ! "OK3"
    case m1: Msg1 => log.info(s"received $m1")
    case m2: Msg2 => log.info(s"received $m2")
    case m3: Msg3 => log.info(s"received $m3")
    case "stop" => log.info(s"received stop msg")
      context.stop(self)
    case _ => log.info("received unknown message")
  }
}

object MyActor {

  case class Msg1(m: String)

  case class Msg2(m: String)

  case class Msg3(m: String)

}

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
    case m1: Msg1 => log.info(s"received $m1")
    case m2: Msg2 => log.info(s"received $m2")
    case m3: Msg3 => log.info(s"received $m3")
      context.system.terminate()
    case _ => log.info("received unknown message")
  }
}

object MyActor {

  case class Msg1(m: String)

  case class Msg2(m: String)

  case class Msg3(m: String)

}

package org.x4444.onakka

import akka.actor.{ActorSystem, Props}
import org.x4444.onakka.MyActor._

/**
  * MySystem
  */
object MySystem {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("mySystem")
    val myActor = system.actorOf(Props[MyActor], "myactor")

    myActor ! "test"

    myActor ! Msg2("m2")

    myActor ! Msg3("kill system")
  }
}

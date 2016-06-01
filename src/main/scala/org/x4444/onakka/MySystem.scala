package org.x4444.onakka

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

/**
  * MySystem
  */
object MySystem {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("mySystem")
    val dispActor = system.actorOf(Props[DispActor], "dispactor")

    implicit val timeout = Timeout(5 seconds)
    val f1 = dispActor ? "test" // enabled by the “ask” import
    val r1 = Await.result(f1, timeout.duration).asInstanceOf[(String, String, String)]
    println(r1)

    dispActor ! "kill"
  }
}

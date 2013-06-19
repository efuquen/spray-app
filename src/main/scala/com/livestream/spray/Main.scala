package com.livestream.spray

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http

object Main extends App {
  implicit val system = ActorSystem("spray-app")
  
  val service = system.actorOf(Props[MetricsServiceActor], "metrics-service")
  
  IO(Http) ! Http.Bind(service, "localhost", port = 8080)
}
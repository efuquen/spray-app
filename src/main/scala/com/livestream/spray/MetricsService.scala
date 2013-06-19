package com.livestream.spray

import spray.routing._
import Directives._

import akka.actor.Actor

class MetricsServiceActor extends Actor with MetricsService {
  def actorRefFactory = context
  
  def receive = runRoute(metricsRoute)
}

trait MetricsService extends HttpService{
  
  implicit def executionContext = actorRefFactory.dispatcher
  
  var metricsMap = Map[String,String]()
  
  val metricsRoute = path("metrics" / Segment) { key =>
    get {
      metricsMap.get(key) match {
        case Some(value) => complete(metricsMap(key))
        case None        => reject()
      }
    } ~ (post & formField("value")) { value =>
      metricsMap += key -> value
      complete("ok")
    }
  }
}
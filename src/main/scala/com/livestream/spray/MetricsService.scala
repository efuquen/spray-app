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
  
  def eventRoute(metricId: Long) = path("event" / LongNumber) { eventId => 
    getMetrics(metricId, eventId, "event")
  }
  
  def platformRoute(metricId: Long) = path("platform" / LongNumber) { platformId => 
    getMetrics(metricId, platformId, "platform")
  }
  
  def getMetrics(
    metricId: Long, metricTypeId: Long, metricType: String
  ) = (get & formField("dimension")) { dimension =>
    complete(s"${metricId}:${metricType}:${metricTypeId}:${dimension}")
  }
  
  val metricsRoute = pathPrefix("metric" / LongNumber) { metricId =>
    eventRoute(metricId) ~ platformRoute(metricId)
  }
}
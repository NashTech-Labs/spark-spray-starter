package com.knoldus.sprayservices

import akka.actor.{ ActorSystem, Props }
import akka.event.Logging
import akka.io.IO
import spray.can.Http
import com.typesafe.config.ConfigFactory
import scala.concurrent.duration.DurationInt

object StartSpark extends App {

  // we need an ActorSystem to host our application in
  implicit val actorSystem = ActorSystem("spark-services")
  implicit val timeout = 30 seconds

  // create and start our service actor
  val service = actorSystem.actorOf(Props[SparkServices], "spark-services")

  // start a new HTTP server on port 8080 with our service actor as the handler
  IO(Http) ! Http.Bind(service, interface = "localhost", port = 8000)

}

package com.knoldus.sprayservices

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Failure
import scala.util.Success
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import akka.actor.Actor
import akka.actor.ActorContext
import spray.http.MediaTypes._
import spray.routing.Directive.pimpApply
import spray.routing.HttpService
import spray.routing.RequestContext
import spray.http.HttpResponse
import spray.http.StatusCodes.OK

trait SparkService extends HttpService {

  val sparkConf: SparkConf = new SparkConf().setAppName("spark-spray-starter").setMaster("local")
  val sc: SparkContext = new SparkContext(sparkConf)

  val sparkRoutes =
    path("spark" / "version") {
      get {
        complete {
          HttpResponse(OK, "Spark version in this template is: " + sc.version)
        }
      }
    }

}

class SparkServices extends Actor with SparkService {
  def actorRefFactory: ActorContext = context
  def receive: Actor.Receive = runRoute(sparkRoutes)
}

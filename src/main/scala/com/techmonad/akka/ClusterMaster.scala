package com.techmonad.akka

import akka.actor.{Actor, ActorLogging, ActorRef}

import scala.concurrent.duration._


class ClusterMaster(clusterRouter: ActorRef) extends Actor with ActorLogging {

  import context.dispatcher
  import FileProcessorActor._
  import ClusterMaster._

  /**
    * Start scheduling
    *
    */
  schedule()

  def receive: Receive = {

    case Watch =>
      log.info("Watching the directory......")
      clusterRouter ! read
      schedule

  }

  def read: FilesInfo = {
    FilesInfo(List(FileInfo("source path", "target path")))
  }

  def schedule() = context.system.scheduler.scheduleOnce(10 seconds, self, Watch)
}


object ClusterMaster {

  private case object Watch

  case object End

}
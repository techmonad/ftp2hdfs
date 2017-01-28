package com.techmonad.app

import akka.actor.{ActorSystem, Props}
import akka.cluster.singleton.{ClusterSingletonManager, ClusterSingletonManagerSettings}
import akka.routing.{FromConfig, RoundRobinPool}
import com.techmonad.akka.{ClusterMaster, FileProcessorActor, SFTP2HDFSActor}


object SFTP2HDFSApp {

  import ClusterMaster._

  def main(args: Array[String]): Unit = {
    val concurrency = Runtime.getRuntime.availableProcessors() * 10
    val system = ActorSystem("SFTP2HDFSClusterSystem")
    val workers = system.actorOf(RoundRobinPool(concurrency).props(Props[SFTP2HDFSActor]), "workers")
    val fileProcesor = system.actorOf(Props(classOf[FileProcessorActor], workers), "fileProcessor")
    val clusterRouter = system.actorOf(FromConfig.props(), "clusterRouter")

    system.actorOf(
      ClusterSingletonManager.props(
        singletonProps = Props(classOf[ClusterMaster], clusterRouter),
        terminationMessage = End,
        settings = ClusterSingletonManagerSettings(system).withRole("master")),
      name = "clusterMaster"

    )

  }


}

package com.techmonad.akka.cluster

import akka.actor.{Actor, ActorLogging}
import akka.cluster.Cluster
import akka.cluster.ClusterEvent._

/**
  * Responsible to listen cluster event
  */

class ClusterListener extends Actor with ActorLogging{

  val cluster = Cluster(context.system)

  override def receive: Receive ={
    case MemberUp(member) =>
      log.info("Member is Up: {}", member.address )
    case UnreachableMember(member) =>
      log.info("Member is unreachable: {}", member)
    case MemberRemoved(member,previousStatus) =>
      log.info("Member is removed: {} after :{}", member.address, previousStatus )

  }

  override def preStart(): Unit = {
    cluster.subscribe(self, initialStateMode = InitialStateAsEvents, classOf[MemberEvent], classOf[UnreachableMember])
    super.preStart()
  }

  override def postStop(): Unit = {
    cluster.unsubscribe(self)
    super.postStop()
  }

}

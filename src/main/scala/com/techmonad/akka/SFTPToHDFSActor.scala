package com.techmonad.akka

import akka.actor.Actor

class SFTPToHDFSActor extends Actor {
  import SFTPToHDFSActor._

  override def receive: Receive = {
    case FileInfo(sourceFilePath, destinationFilePath) =>
      //
  }
}

object SFTPToHDFSActor {
  case class FileInfo(sourceFilePath:String, destinationFilePath:String )
}



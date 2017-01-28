package com.techmonad.akka

import akka.actor.Actor

class SFTP2HDFSActor extends Actor {

  import FileProcessorActor._

  override def receive: Receive = {
    case FileInfo(sourcePath, targetPath) =>
      println(sourcePath + "moving file from source to target.............." + targetPath)
  }
}




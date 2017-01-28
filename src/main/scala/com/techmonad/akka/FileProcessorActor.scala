package com.techmonad.akka

import akka.actor.{Actor, ActorRef}



class FileProcessorActor(workers:ActorRef) extends Actor {
  import FileProcessorActor._

  override def receive: Receive = {
    case FilesInfo(filesInfo) =>
      filesInfo foreach(fileInfo => workers ! fileInfo )
  }

}

object FileProcessorActor {
  case class FileInfo(sourcePath:String, targetPath:String )
  case class FilesInfo(filePaths:List[FileInfo])
}


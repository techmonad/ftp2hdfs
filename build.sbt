name := "sftp2hdfs"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++=Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.16",
  "com.typesafe.akka" %% "akka-cluster" % "2.4.16"
)
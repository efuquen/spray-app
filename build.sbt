organization := "com.livestream"

name := "spray-app"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
  "io.spray" % "spray-routing" % "1.2-M8",
  "io.spray" % "spray-can" % "1.2-M8",
  "com.typesafe.akka" %% "akka-actor" % "2.2.0-RC1"
)

resolvers ++= Seq(
  "spray repo" at "http://repo.spray.io",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
)

organization := "com.kalmanb"

name := "remote-peer-dev"

scalaVersion := "2.11.0"

libraryDependencies ++= Seq(
  "io.spray" % "spray-json_2.11.0-RC4" % "1.2.6",
  "com.typesafe.akka" %% "akka-actor" % "2.3.2",
  "com.typesafe.akka" %% "akka-slf4j" % "2.3.2",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.2" % "test",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "org.scalaz" %% "scalaz-core" % "7.0.6",
  "com.sksamuel.diff" % "diff" % "1.1.11",
  "commons-codec" % "commons-codec" % "1.9",
  // Test
  "org.scalatest" %% "scalatest" % "2.1.3" % "test",
  "junit" % "junit" % "4.11" % "test",
  "org.mockito" % "mockito-all" % "1.9.5" % "test" 
)

scalacOptions ++= Seq( "-deprecation", "-unchecked", "-feature" ,"-language:postfixOps" )






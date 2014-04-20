organization := "com.kalmanb"

name := "peer-dev"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "io.spray" % "spray-client" % "1.3.1",
  "io.spray" % "spray-can" % "1.3.1",
  // akka 2.2.x required for spray
  "com.typesafe.akka" %% "akka-actor" % "2.3.2",
  "com.typesafe.akka" %% "akka-slf4j" % "2.3.2",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.2" % "test",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "org.scalaz" %% "scalaz-core" % "7.0.6",
  // Test
  "org.scalatest" %% "scalatest" % "2.1.3" % "test",
  "junit" % "junit" % "4.11" % "test",
  "org.mockito" % "mockito-all" % "1.9.5" % "test" 
)

scalacOptions ++= Seq( "-deprecation", "-unchecked", "-feature" ,"-language:postfixOps" )






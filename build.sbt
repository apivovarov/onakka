lazy val appSettings = Seq(
  organization := "org.x4444",
  name := "onakka",
  version := "1.0.0"
)

// Those settings should be the same as in alchemy!
lazy val scalaVersion_ = "2.11.8" // should be the same as spark
lazy val javaVersion = "1.8" // should be the same as spark

lazy val scalaTestVersion = "2.2.5"

scalaVersion in Global := scalaVersion_

scalacOptions in Global ++= Seq(
  "-deprecation",
  "-feature",
  "-target:jvm-" + javaVersion,
  "-Xfatal-warnings",
  "-Xlint"
)

javacOptions in Global ++= Seq(
  "-encoding", "UTF-8",
  "-source", javaVersion,
  "-target", javaVersion
)

lazy val auxLib = Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.6"
)

lazy val testLib = Seq(
  "org.scalatest" %% "scalatest" % scalaTestVersion % Test withSources() withJavadoc()
)

lazy val root = (project in file("."))
  .settings(appSettings: _*)
  .settings(
    libraryDependencies ++= auxLib,
    libraryDependencies ++= testLib
  )


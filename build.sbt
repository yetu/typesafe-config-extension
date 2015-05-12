name := """typesafe-config-extension"""

version := "0.1.0"

organization := "com.yetu"

scalaVersion := "2.11.5"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

libraryDependencies += "com.typesafe" % "config" % "1.3.0"

resolvers += "Bintray Repo" at "http://dl.bintray.com/yetu/maven/"

// ----------- publishing settings -----------------------------------
// http://www.scala-sbt.org/0.13.5/docs/Detailed-Topics/Publishing.html
// -------------------------------------------------------------------

crossScalaVersions := Seq("2.10.4", "2.11.5")

// sbt-release plugin settings:
releaseSettings

publishMavenStyle := true

publishArtifact in (Test, packageBin) := true

// settings for bintray publishing

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

bintrayOrganization := Some("yetu")
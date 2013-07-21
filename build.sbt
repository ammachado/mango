//
// Copyright (C) 2013 The Mango Authors
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

name := "mango"

organization := "org.feijoas"

version := "0.7"

scalaVersion := "2.10.1"

sbtVersion := "0.12"

// resolvers
resolvers += Classpaths.typesafeSnapshots

resolvers ++= Seq(
  "Sonatype Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype Releases" at "http://oss.sonatype.org/content/repositories/releases"
)

// compile dependencies
libraryDependencies += "com.google.guava" % "guava" % "14.0.1"

libraryDependencies += "com.google.code.findbugs" % "jsr305" % "1.3.+"

// test dependencies
libraryDependencies += "com.google.guava" % "guava-testlib" % "14.0.1" % "test"

libraryDependencies += "junit" % "junit" % "4.11" % "test"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0.M5b" % "test"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.10.0" % "test"

libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.0.1" % "test"

libraryDependencies += "org.mockito" % "mockito-core" % "1.9.5" % "test"

// Scala compiler options
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-language:implicitConversions,reflectiveCalls,postfixOps,higherKinds,existentials")

// Scaladoc title
scalacOptions in (Compile, doc) ++= Opts.doc.title("Mango")

// Scaladoc title page
scalacOptions in (Compile, doc) ++= Seq("-doc-root-content", "rootdoc.txt")

// Show durations for tests
testOptions in Test += Tests.Argument("-oD")

// Disable parallel execution of tests
parallelExecution in Test := false

// Using the Scala version in output paths and artifacts
crossPaths := true

// publish test jar, sources, and docs
publishArtifact in Test := false

// Publish Maven style
publishMavenStyle := true

// Remove the repositories for optional dependencies
pomIncludeRepository := { _ => false }

// SNAPSHOT versions go to the /snapshot repository while other versions go to the /releases repository
publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

// POM metadata that isn't generated by SBT
pomExtra := (
  <url>mango.feijoas.org</url>
  <licenses>
    <license>
      <name>The Apache Software License, Version 2.0</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>https://github.com/feijoas/mango.git</url>
    <connection>https://github.com/feijoas/mango.git</connection>
  </scm>
  <developers>
    <developer>
      <id>mschneiderwng</id>
      <name>Markus Schneider</name>
    </developer>
  </developers>)
name := "hello-slick-oracle"

version := "0.1.0"

scalaVersion := "2.10.7"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "2.1.0"
)

name := "hello-slick-oracle"

version := "0.1.0"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "ch.qos.logback" % "logback-classic" % "1.1.2"   // logging backend
)


//unmanagedJars in Compile += file("lib/ojdbc7.jar")

// Exclude Oracle JDBC jars from assembly
// assemblyExcludedJars in assembly := {
//   val cp = (fullClasspath in assembly).value
//   cp.filter(_.data.getName.startsWith("ojdbc"))
// }


mainClass := Some("Main")

// assembly settings
// mainClass in assembly := Some("Main")

// assemblyMergeStrategy in assembly := {
//   case PathList("META-INF", xs @ _*) => MergeStrategy.discard
//   case x => MergeStrategy.first
//}


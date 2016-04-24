scalaVersion := "2.11.8"

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:existentials",
  "-language:experimental.macros",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-unchecked",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Xfuture",
  "-Xlint"
  // ,"-Ymacro-debug-lite"
)

libraryDependencies ++= {
  val specsV = "3.7.2"
  Seq(
  "com.chuusai" %% "shapeless" % "2.3.0",
  "org.scala-lang" % "scala-reflect" % "2.11.8",
  "org.specs2" %% "specs2-core" % specsV % "test"
  )
}

name := "magic-of-integrations"

version := "0.1"

scalaVersion := "2.12.8"

val http4sVersion      = "0.20.10"
val circeVersion       = "0.11.0"
lazy val doobieVersion = "0.7.0"

libraryDependencies ++= Seq(
  // http4s
  "org.slf4j"  % "slf4j-simple"         % "1.7.28",
  "org.http4s" %% "http4s-dsl"          % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,
  // circe
  "org.http4s" %% "http4s-circe"  % http4sVersion,
  "io.circe"   %% "circe-generic" % circeVersion,
  // doobie
  "org.tpolecat"   %% "doobie-core" % doobieVersion,
  "com.h2database" % "h2"           % "1.4.199",
  // quill
  "org.tpolecat" %% "doobie-quill" % "0.7.0",
  // atto
  "org.tpolecat" %% "atto-core" % "0.6.5",
  // refined
  "eu.timepit" %% "refined"       % "0.9.9",
  "io.circe"   %% "circe-refined" % circeVersion,
  // decline
  "com.monovore" %% "decline" % "0.5.0",
)

addCompilerPlugin("org.scalamacros" % "paradise"        % "2.1.0" cross CrossVersion.full)
addCompilerPlugin("org.typelevel"   %% "kind-projector" % "0.10.3")

scalacOptions ++= Seq("-Ypartial-unification")

name := "magic-of-integrations"

version := "0.1"

scalaVersion := "2.12.8"

val http4sVersion      = "0.20.10"
val circeVersion       = "0.12.1"
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
  //sttp
  "com.softwaremill.sttp" %% "core"                          % "1.6.7",
  "com.softwaremill.sttp" %% "circe"                         % "1.6.7",
  "com.softwaremill.sttp" %% "async-http-client-backend-fs2" % "1.6.7",
  "io.circe"              %% "circe-fs2"                     % "0.12.0",
  //console4cats
  "dev.profunktor" %% "console4cats" % "0.8.0",
  //ciris
  "is.cir" %% "ciris" % "1.0.0",
)

addCompilerPlugin("org.scalamacros" % "paradise"        % "2.1.0" cross CrossVersion.full)
addCompilerPlugin("org.typelevel"   %% "kind-projector" % "0.10.3")

scalacOptions ++= Seq("-Ypartial-unification")

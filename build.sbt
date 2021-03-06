val projectName = "guardrail-root"
name := projectName
organization in ThisBuild := "com.twilio"
licenses in ThisBuild += ("MIT", url("http://opensource.org/licenses/MIT"))
// Project version is determined by sbt-git based on the most recent tag
// Publishing information is defined in `bintray.sbt`

enablePlugins(GitVersioning)
git.useGitDescribe := true

crossScalaVersions := Seq("2.11.12", "2.12.3")
scalaVersion in ThisBuild := crossScalaVersions.value.last

scalafmtOnCompile in ThisBuild := true
scalafmtFailTest in ThisBuild := false

val akkaVersion       = "10.0.14"
val catsVersion       = "1.4.0"
val catsEffectVersion = "1.0.0"
val circeVersion      = "0.10.1"
val http4sVersion     = "0.19.0"
val scalatestVersion  = "3.0.5"

mainClass in assembly := Some("com.twilio.guardrail.CLI")

lazy val runScalaExample: TaskKey[Unit] = taskKey[Unit]("Run scala generator with example args")
fullRunTask(
  runScalaExample,
  Test,
  "com.twilio.guardrail.CLI",
  """
  --defaults --import support.PositiveLong
  --client --specPath modules/sample/src/main/resources/polymorphism.yaml --outputPath modules/sample/src/main/scala --packageName polymorphism.client.http4s --framework http4s
  --client --specPath modules/sample/src/main/resources/polymorphism.yaml --outputPath modules/sample/src/main/scala --packageName polymorphism.client.akkaHttp --framework akka-http
  --server --specPath modules/sample/src/main/resources/polymorphism.yaml --outputPath modules/sample/src/main/scala --packageName polymorphism.server.http4s --framework http4s
  --server --specPath modules/sample/src/main/resources/polymorphism.yaml --outputPath modules/sample/src/main/scala --packageName polymorphism.server.akkaHttp --framework akka-http
  --client --specPath modules/sample/src/main/resources/petstore.json --outputPath modules/sample/src/main/scala --packageName clients.http4s --framework http4s
  --client --specPath modules/sample/src/main/resources/petstore.json --outputPath modules/sample/src/main/scala --packageName clients.akkaHttp --framework akka-http
  --server --specPath modules/sample/src/main/resources/petstore.json --outputPath modules/sample/src/main/scala --packageName servers.http4s --framework http4s
  --server --specPath modules/sample/src/main/resources/petstore.json --outputPath modules/sample/src/main/scala --packageName servers.akkaHttp --framework akka-http
  --client --specPath modules/sample/src/main/resources/plain.json --outputPath modules/sample/src/main/scala --packageName tests.dtos.http4s --framework http4s
  --client --specPath modules/sample/src/main/resources/plain.json --outputPath modules/sample/src/main/scala --packageName tests.dtos.akkaHttp --framework akka-http
  --client --specPath modules/sample/src/main/resources/contentType-textPlain.yaml --outputPath modules/sample/src/main/scala --packageName tests.contentTypes.textPlain.client.http4s --framework http4s
  --client --specPath modules/sample/src/main/resources/contentType-textPlain.yaml --outputPath modules/sample/src/main/scala --packageName tests.contentTypes.textPlain.client.akkaHttp --framework akka-http
  --server --specPath modules/sample/src/main/resources/contentType-textPlain.yaml --outputPath modules/sample/src/main/scala --packageName tests.contentTypes.textPlain.server.http4s --framework http4s
  --server --specPath modules/sample/src/main/resources/contentType-textPlain.yaml --outputPath modules/sample/src/main/scala --packageName tests.contentTypes.textPlain.server.akkaHttp --framework akka-http
  --server --specPath modules/sample/src/main/resources/raw-response.yaml --outputPath modules/sample/src/main/scala --packageName raw.server.http4s --framework http4s
  --server --specPath modules/sample/src/main/resources/raw-response.yaml --outputPath modules/sample/src/main/scala --packageName raw.server.akkaHttp --framework akka-http
  --server --specPath modules/sample/src/test/resources/server1.yaml --outputPath modules/sample/src/main/scala --packageName tracer.servers.http4s --tracing --framework http4s
  --server --specPath modules/sample/src/test/resources/server1.yaml --outputPath modules/sample/src/main/scala --packageName tracer.servers.akkaHttp --tracing --framework akka-http
  --client --specPath modules/sample/src/test/resources/server1.yaml --outputPath modules/sample/src/main/scala --packageName tracer.clients.http4s --tracing --framework http4s
  --client --specPath modules/sample/src/test/resources/server1.yaml --outputPath modules/sample/src/main/scala --packageName tracer.clients.akkaHttp --tracing --framework akka-http
  --server --specPath modules/sample/src/test/resources/server2.yaml --outputPath modules/sample/src/main/scala --packageName tracer.servers.http4s --tracing --framework http4s
  --server --specPath modules/sample/src/test/resources/server2.yaml --outputPath modules/sample/src/main/scala --packageName tracer.servers.akkaHttp --tracing --framework akka-http
  --client --specPath modules/sample/src/test/resources/server2.yaml --outputPath modules/sample/src/main/scala --packageName tracer.clients.http4s --tracing --framework http4s
  --client --specPath modules/sample/src/test/resources/server2.yaml --outputPath modules/sample/src/main/scala --packageName tracer.clients.akkaHttp --tracing --framework akka-http
  --client --specPath modules/sample/src/main/resources/alias.yaml --outputPath modules/sample/src/main/scala --packageName alias.client.http4s --framework http4s
  --client --specPath modules/sample/src/main/resources/alias.yaml --outputPath modules/sample/src/main/scala --packageName alias.client.akkaHttp --framework akka-http
  --server --specPath modules/sample/src/main/resources/alias.yaml --outputPath modules/sample/src/main/scala --packageName alias.server.http4s --framework http4s
  --server --specPath modules/sample/src/main/resources/alias.yaml --outputPath modules/sample/src/main/scala --packageName alias.server.akkaHttp --framework akka-http
  --client --specPath modules/sample/src/main/resources/edgecases/defaults.yaml --outputPath modules/sample/src/main/scala --packageName edgecases.defaults.http4s --framework http4s
  --client --specPath modules/sample/src/main/resources/edgecases/defaults.yaml --outputPath modules/sample/src/main/scala --packageName edgecases.defaults.akkaHttp --framework akka-http
  --client --specPath modules/sample/src/main/resources/custom-header-type.yaml --outputPath modules/sample/src/main/scala --packageName tests.customTypes.customHeader.client.http4s --framework http4s
  --client --specPath modules/sample/src/main/resources/custom-header-type.yaml --outputPath modules/sample/src/main/scala --packageName tests.customTypes.customHeader.client.akkaHttp --framework akka-http
  --server --specPath modules/sample/src/main/resources/custom-header-type.yaml --outputPath modules/sample/src/main/scala --packageName tests.customTypes.customHeader.server.http4s --framework http4s
  --server --specPath modules/sample/src/main/resources/custom-header-type.yaml --outputPath modules/sample/src/main/scala --packageName tests.customTypes.customHeader.server.akkaHttp --framework akka-http
  --client --specPath modules/sample/src/main/resources/formData.yaml --outputPath modules/sample/src/main/scala --packageName form.client.http4s --framework http4s
  --client --specPath modules/sample/src/main/resources/formData.yaml --outputPath modules/sample/src/main/scala --packageName form.client.akkaHttp --framework akka-http
  --server --specPath modules/sample/src/main/resources/formData.yaml --outputPath modules/sample/src/main/scala --packageName form.server.http4s --framework http4s
  --server --specPath modules/sample/src/main/resources/formData.yaml --outputPath modules/sample/src/main/scala --packageName form.server.akkaHttp --framework akka-http
  --server --specPath modules/sample/src/main/resources/issues/issue121.yaml --outputPath modules/sample/src/main/scala --packageName issues.issue121.server.akkaHttp --framework akka-http
  --client --specPath modules/sample/src/main/resources/issues/issue121.yaml --outputPath modules/sample/src/main/scala --packageName issues.issue121.client.akkaHttp --framework akka-http
  --server --specPath modules/sample/src/main/resources/issues/issue121.yaml --outputPath modules/sample/src/main/scala --packageName issues.issue121.server.http4s --framework http4s
  --client --specPath modules/sample/src/main/resources/issues/issue121.yaml --outputPath modules/sample/src/main/scala --packageName issues.issue121.client.http4s --framework http4s
  --server --specPath modules/sample/src/main/resources/issues/issue127.yaml --outputPath modules/sample/src/main/scala --packageName issues.issue127
  --server --specPath modules/sample/src/main/resources/issues/issue143.yaml --outputPath modules/sample/src/main/scala --packageName issues.issue143
  --client --specPath modules/sample/src/main/resources/issues/issue148.yaml --outputPath modules/sample/src/main/scala --packageName issues.issue148.client.http4s --framework http4s
  --client --specPath modules/sample/src/main/resources/issues/issue148.yaml --outputPath modules/sample/src/main/scala --packageName issues.issue148.client.akkaHttp --framework akka-http
  --server --specPath modules/sample/src/main/resources/issues/issue148.yaml --outputPath modules/sample/src/main/scala --packageName issues.issue148.server.http4s --framework http4s
  --server --specPath modules/sample/src/main/resources/issues/issue148.yaml --outputPath modules/sample/src/main/scala --packageName issues.issue148.server.akkaHttp --framework akka-http
""".replaceAllLiterally("\n", " ").split(' ').filter(_.nonEmpty): _*
)

artifact in (Compile, assembly) := {
  (artifact in (Compile, assembly)).value
    .withClassifier(Option("assembly"))
}

addArtifact(artifact in (Compile, assembly), assembly)

addCommandAlias("cli", "runMain com.twilio.guardrail.CLI")
addCommandAlias(
  "format",
  "; codegen/scalafmt ; codegen/test:scalafmt ; scalafmt ; codegen/test:scalafmt ; sample/scalafmt ; sample/test:scalafmt"
)

val resetSample = TaskKey[Unit]("resetSample", "Reset sample module")

resetSample := {
  import scala.sys.process._
  "git clean -fdx modules/sample/src modules/sample/target" !
}

addCommandAlias("example", "; resetSample ; runScalaExample ; sample/test")
addCommandAlias("scalaTestSuite", "; codegen/test ; resetSample; runScalaExample ; sample/test")
addCommandAlias("testSuite", "; scalaTestSuite")

addCommandAlias(
  "publishBintray",
  "; set publishTo in codegen := (publishTo in bintray in codegen).value; codegen/publishSigned"
)
addCommandAlias(
  "publishSonatype",
  "; set publishTo in codegen := (sonatypePublishTo in codegen).value; codegen/publishSigned"
)

resolvers += Resolver.sonatypeRepo("releases")
addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.9")
addCompilerPlugin("org.spire-math" % "kind-projector"  % "0.9.9" cross CrossVersion.binary)

publishMavenStyle := true

val testDependencies = Seq(
  "org.scalatest" %% "scalatest" % scalatestVersion % Test
)

val codegenSettings = Seq(
  addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.9"),
  addCompilerPlugin("org.spire-math" % "kind-projector"  % "0.9.9" cross CrossVersion.binary),
  libraryDependencies ++= testDependencies ++ Seq(
    "org.scalameta" %% "scalameta"     % "4.1.0",
    "io.swagger"    % "swagger-parser" % "1.0.39",
    "org.tpolecat"  %% "atto-core"     % "0.6.3",
    "org.typelevel" %% "cats-core"     % catsVersion,
    "org.typelevel" %% "cats-kernel"   % catsVersion,
    "org.typelevel" %% "cats-macros"   % catsVersion,
    "org.typelevel" %% "cats-free"     % catsVersion
  )
  // Dev
  ,
  scalacOptions in ThisBuild ++= Seq(
    "-Ypartial-unification",
    "-language:higherKinds",
    "-Xexperimental",
    "-Ydelambdafy:method",
    // "-Yno-adapted-args",
    "-Xlint:_,-unused",
    "-feature",
    "-unchecked",
    "-deprecation",
    "-target:jvm-1.8",
    "-encoding",
    "utf8"
  ),
  parallelExecution in Test := true,
  fork := true
)

lazy val root = (project in file("."))
  .settings(
    libraryDependencies ++= testDependencies,
    skip in publish := true
  )
  .dependsOn(codegen)

lazy val codegen = (project in file("modules/codegen"))
  .settings(
    (name := "guardrail") +:
      codegenSettings,
    bintrayRepository := {
      if (isSnapshot.value) "snapshots"
      else "releases"
    },
    bintrayPackageLabels := Seq(
      "codegen",
      "openapi",
      "swagger"
    ),
    description := "Principled code generation for Scala services from OpenAPI specifications",
    homepage := Some(url("https://github.com/twilio/guardrail")),
    scmInfo := Some(
      ScmInfo(
        url("https://github.com/twilio/guardrail"),
        "scm:git@github.com:twilio/guardrail.git"
      )
    ),
    developers := List(
      Developer(
        id = "blast_hardcheese",
        name = "Devon Stewart",
        email = "blast@hardchee.se",
        url = url("http://hardchee.se/")
      )
    )
  )

lazy val sample = (project in file("modules/sample"))
  .settings(
    codegenSettings,
    initialCommands in console := """
      |import cats._
      |import cats.data.EitherT
      |import cats.implicits._
      |import cats.effect.IO
      |import io.circe._
      |import java.time._
      |import org.http4s._
      |import org.http4s.client._
      |import org.http4s.client.blaze._
      |import org.http4s.dsl._
      |import org.http4s.multipart._
      |import scala.concurrent.Await
      |import scala.concurrent.ExecutionContext.Implicits.global
      |import scala.concurrent.duration._
      |import scala.meta._
      |""".stripMargin,
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http"           % akkaVersion,
      "com.typesafe.akka" %% "akka-http-testkit"   % akkaVersion,
      "io.circe"          %% "circe-core"          % circeVersion,
      "io.circe"          %% "circe-generic"       % circeVersion,
      "io.circe"          %% "circe-java8"         % circeVersion,
      "io.circe"          %% "circe-parser"        % circeVersion,
      "org.http4s"        %% "http4s-blaze-client" % http4sVersion,
      "org.http4s"        %% "http4s-blaze-server" % http4sVersion,
      "org.http4s"        %% "http4s-circe"        % http4sVersion,
      "org.http4s"        %% "http4s-dsl"          % http4sVersion,
      "org.scalatest"     %% "scalatest"           % scalatestVersion % Test,
      "org.typelevel"     %% "cats-core"           % catsVersion,
      "org.typelevel"     %% "cats-effect"         % catsEffectVersion
    ),
    skip in publish := true,
    scalafmtOnCompile := false
  )

watchSources ++= (baseDirectory.value / "modules/sample/src/test" ** "*.scala").get

logBuffered in Test := false

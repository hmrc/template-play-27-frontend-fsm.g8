import JavaScriptBuild.{javaScriptDirectory, javaScriptSettings}
import sbt.Tests.{Group, SubProcess}
import uk.gov.hmrc.SbtAutoBuildPlugin
import uk.gov.hmrc.sbtdistributables.SbtDistributablesPlugin._

lazy val scoverageSettings = {
  import scoverage.ScoverageKeys
  Seq(
    // Semicolon-separated list of regexes matching classes to exclude
    ScoverageKeys.coverageExcludedPackages := """uk\\.gov\\.hmrc\\.BuildInfo;.*\\.Routes;.*\\.RoutesPrefix;.*Filters?;MicroserviceAuditConnector;Module;GraphiteStartUp;.*\\.Reverse[^.]*""",
    ScoverageKeys.coverageMinimum := 80.00,
    ScoverageKeys.coverageFailOnMinimum := false,
    ScoverageKeys.coverageHighlighting := true,
    parallelExecution in Test := false
  )
}

lazy val compileDeps = Seq(
  "uk.gov.hmrc"                  %% "bootstrap-frontend-play-27" % "4.1.0",
  "uk.gov.hmrc"                  %% "auth-client"                % "4.0.0-play-27",
  "uk.gov.hmrc"                  %% "play-fsm"                   % "0.83.0-play-27",
  "uk.gov.hmrc"                  %% "domain"                     % "5.10.0-play-27",
  "uk.gov.hmrc.mongo"            %% "hmrc-mongo-play-27"         % "0.47.0",
  "uk.gov.hmrc"                  %% "json-encryption"            % "4.8.0-play-27",
  "uk.gov.hmrc"                  %% "play-frontend-govuk"        % "0.71.0-play-27",
  "uk.gov.hmrc"                  %% "play-frontend-hmrc"         % "0.60.0-play-27",
  "com.googlecode.libphonenumber" % "libphonenumber"             % "8.12.19",
  "com.sun.mail"                  % "javax.mail"                 % "1.6.2"
)

def testDeps(scope: String) =
  Seq(
    "org.scalatest"          %% "scalatest"          % "3.2.6"  % scope,
    "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3"  % scope,
    "com.github.tomakehurst"  % "wiremock-jre8"      % "2.27.2" % scope,
    "com.vladsch.flexmark"    % "flexmark-all"       % "0.36.8" % scope
  )

lazy val root = (project in file("."))
  .settings(
    name := "$serviceNameHyphen$",
    organization := "uk.gov.hmrc",
    scalaVersion := "2.12.12",
    PlayKeys.playDefaultPort := $serviceTargetPort$,
    TwirlKeys.templateImports ++= Seq(
      "play.twirl.api.HtmlFormat",
      "uk.gov.hmrc.govukfrontend.views.html.components._",
      "uk.gov.hmrc.hmrcfrontend.views.html.{components => hmrcComponents}",
      "uk.gov.hmrc.govukfrontend.views.html.helpers._",
      "$package$.views.html.components",
      "$package$.views.ViewHelpers._"
    ),
    PlayKeys.playRunHooks += Webpack(javaScriptDirectory.value),
    libraryDependencies ++= compileDeps ++ testDeps("test") ++ testDeps("it"),
    publishingSettings,
    javaScriptSettings,
    scoverageSettings,
    unmanagedResourceDirectories in Compile += baseDirectory.value / "resources",
    scalafmtOnCompile in Compile := true,
    scalafmtOnCompile in Test := true,
    majorVersion := 0,
    // concatenate js
    Concat.groups := Seq(
      "javascripts/application.js" ->
        group(
          Seq(
            "lib/govuk-frontend/govuk/all.js",
            "lib/hmrc-frontend/hmrc/all.js",
            "build/application.min.js"
          )
        )
    ),
    // prevent removal of unused code which generates warning errors due to use of third-party libs
    uglifyCompressOptions := Seq("unused=false", "dead_code=false"),
    // below line required to force asset pipeline to operate in dev rather than only prod
    pipelineStages in Assets := Seq(concat, uglify),
    // only compress files generated by concat
    includeFilter in uglify := GlobFilter("application.js")
  )
  .configs(IntegrationTest)
  .settings(
    Keys.fork in IntegrationTest := false,
    Defaults.itSettings,
    unmanagedSourceDirectories in IntegrationTest += baseDirectory(_ / "it").value,
    parallelExecution in IntegrationTest := false,
    testGrouping in IntegrationTest := oneForkedJvmPerTest((definedTests in IntegrationTest).value),
    scalafmtOnCompile in IntegrationTest := true
  )
  .disablePlugins(JUnitXmlReportPlugin) //Required to prevent https://github.com/scalatest/scalatest/issues/1427
  .enablePlugins(PlayScala, SbtAutoBuildPlugin, SbtGitVersioning, SbtDistributablesPlugin)

inConfig(IntegrationTest)(org.scalafmt.sbt.ScalafmtPlugin.scalafmtConfigSettings)

def oneForkedJvmPerTest(tests: Seq[TestDefinition]) =
  tests.map { test =>
    new Group(test.name, Seq(test), SubProcess(ForkOptions().withRunJVMOptions(Vector(s"-Dtest.name=\${test.name}"))))
  }

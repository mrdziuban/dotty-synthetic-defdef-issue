lazy val baseSettings = Seq(
  organization := "com.example",
  scalaVersion := "3.3.1-RC4",
  version      := "0.1.0-SNAPSHOT",
)

lazy val plugin = project.in(file("plugin"))
  .settings(baseSettings)
  .settings(
    name := "synthetic-defdef-issue-plugin",
    libraryDependencies += "org.scala-lang" %% "scala3-compiler" % scalaVersion.value % "provided",
  )

lazy val tests = project.in(file("tests"))
  .settings(baseSettings)
  .settings(
    name := "synthetic-defdef-issue-tests",
    organization := "com.example",
    scalacOptions ++= {
      val jar = (plugin / Compile / Keys.`package`).value
      Seq(
        s"-Xplugin:${jar.getAbsolutePath}",
        s"-Jdummy=${jar.lastModified}",
      )
    },
  )

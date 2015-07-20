name := "spark-spray-starter"

version := "1.0"

scalaVersion := "2.10.4"

organization := "com.knoldus"

libraryDependencies ++= Seq(
                      "org.apache.spark"  %% "spark-core"      % "1.4.1",
		              "io.spray"          %% "spray-can"       % "1.3.3",
	                  "io.spray"          %% "spray-routing"   % "1.3.3"
	                   )
	                   
assembleArtifact in packageScala := false  // We don't need the Scala library, Spark already includes it

mergeStrategy in assembly := {
      case m if m.toLowerCase.endsWith("manifest.mf") => MergeStrategy.discard
      case m if m.toLowerCase.matches("meta-inf.*\\.sf$") => MergeStrategy.discard
      case "reference.conf" => MergeStrategy.concat
      case _ => MergeStrategy.first
    }
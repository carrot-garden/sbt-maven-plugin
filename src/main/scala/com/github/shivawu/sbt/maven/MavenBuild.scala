package com.github.shivawu.sbt.maven

import sbt._
import Keys._

abstract class MavenBuild extends PomBuild with SelectorDSL with GlobFactory with OrFactory {
	MavenBuild.instantiate

  implicit def stringToSelector(s: String) = produce(s)
}

object MavenBuild {
	private var instantiated = false

	private[MavenBuild] def instantiate { instantiated = true }

	def isInstantiated = instantiated
}

trait PomBuild extends Build {
  protected lazy val pom = Pom(new java.io.File("./pom.xml"))

  lazy val root = pom.project

  override def projects = root :: pom.allModules
}

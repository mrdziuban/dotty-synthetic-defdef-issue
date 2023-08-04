package com.example

import dotty.tools.dotc.ast.tpd.*
import dotty.tools.dotc.core.Contexts.Context
import dotty.tools.dotc.core.Flags
import dotty.tools.dotc.plugins.{PluginPhase, StandardPlugin}

class PrintSyntheticPlugin extends StandardPlugin { self =>
  val name = "printSynthetic"
  val description = name

  override def init(opts: List[String]): List[PluginPhase] = List(phase)

  object phase extends PluginPhase {
    override val phaseName = self.name
    override val runsAfter = Set("posttyper")

    override def transformDefDef(tree: DefDef)(using Context): Tree = {
      println(
        s"def ${tree.name}:\n" ++
        s"  mods.is(Flags.Synthetic): ${tree.mods.is(Flags.Synthetic)}\n" ++
        s"  symbol.is(Flags.Synthetic): ${tree.symbol.is(Flags.Synthetic)}\n"
      )
      tree
    }
  }
}

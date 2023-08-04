## Dotty inconsistency between `tree.mods.is(Flags.Synthetic)` and `tree.symbol.is(Flags.Synthetic)`

To demonstrate the issue:

1. Clone this repo
2. `cd` into it
3. Run `sbt tests/compile`

You should see output for every method generated by the compiler for
[`case class Foo()`](tests/src/main/scala/PrintSyntheticTest.scala#L3) along with the result of calling
`tree.mods.is(Flags.Synthetic)` and `tree.symbol.is(Flags.Synthetic)` for the method.

As of scala 3.3.1-RC4, the only methods where they align are `<init>`, `copy`, `apply`, `unapply`, and `toString`.
The full output is:

```
def <init>:
  mods.is(Flags.Synthetic): false
  symbol.is(Flags.Synthetic): false

def hashCode:
  mods.is(Flags.Synthetic): false
  symbol.is(Flags.Synthetic): true

def equals:
  mods.is(Flags.Synthetic): false
  symbol.is(Flags.Synthetic): true

def toString:
  mods.is(Flags.Synthetic): false
  symbol.is(Flags.Synthetic): true

def canEqual:
  mods.is(Flags.Synthetic): false
  symbol.is(Flags.Synthetic): true

def productArity:
  mods.is(Flags.Synthetic): false
  symbol.is(Flags.Synthetic): true

def productPrefix:
  mods.is(Flags.Synthetic): false
  symbol.is(Flags.Synthetic): true

def productElement:
  mods.is(Flags.Synthetic): false
  symbol.is(Flags.Synthetic): true

def productElementName:
  mods.is(Flags.Synthetic): false
  symbol.is(Flags.Synthetic): true

def copy:
  mods.is(Flags.Synthetic): true
  symbol.is(Flags.Synthetic): true

def <init>:
  mods.is(Flags.Synthetic): false
  symbol.is(Flags.Synthetic): false

def writeReplace:
  mods.is(Flags.Synthetic): false
  symbol.is(Flags.Synthetic): true

def apply:
  mods.is(Flags.Synthetic): true
  symbol.is(Flags.Synthetic): true

def unapply:
  mods.is(Flags.Synthetic): true
  symbol.is(Flags.Synthetic): true

def toString:
  mods.is(Flags.Synthetic): true
  symbol.is(Flags.Synthetic): true

def fromProduct:
  mods.is(Flags.Synthetic): false
  symbol.is(Flags.Synthetic): true
```

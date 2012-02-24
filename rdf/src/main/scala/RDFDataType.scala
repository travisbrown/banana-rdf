package org.w3.rdf

trait RDFDataType {
  type Graph
  type Triple
  type Node
  type IRI <: Node
  type BNode <: Node
  type Literal <: Node
  type TypedLiteral <: Literal
  type LangLiteral <: Literal
  type Lang
}
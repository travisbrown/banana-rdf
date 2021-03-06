package org.w3.banana

import scala.concurrent.Future

/**
 * to execute Sparql queries
 */
trait SparqlEngine[Rdf <: RDF] extends Any {

  def executeSelect(query: Rdf#SelectQuery, bindings: Map[String, Rdf#Node]): Future[Rdf#Solutions]

  def executeConstruct(query: Rdf#ConstructQuery, bindings: Map[String, Rdf#Node]): Future[Rdf#Graph]

  def executeAsk(query: Rdf#AskQuery, bindings: Map[String, Rdf#Node]): Future[Boolean]

  def executeSelect(query: Rdf#SelectQuery): Future[Rdf#Solutions] = executeSelect(query, Map.empty)

  def executeConstruct(query: Rdf#ConstructQuery): Future[Rdf#Graph] = executeConstruct(query, Map.empty)

  def executeAsk(query: Rdf#AskQuery): Future[Boolean] = executeAsk(query, Map.empty)

}

object SparqlEngine {

  def apply[Rdf <: RDF](store: RDFStore[Rdf]): SparqlEngine[Rdf] = new SparqlEngine[Rdf] {

    def executeSelect(query: Rdf#SelectQuery, bindings: Map[String, Rdf#Node]): Future[Rdf#Solutions] =
      store.execute(Command.select(query, bindings))

    def executeConstruct(query: Rdf#ConstructQuery, bindings: Map[String, Rdf#Node]): Future[Rdf#Graph] =
      store.execute(Command.construct(query, bindings))

    def executeAsk(query: Rdf#AskQuery, bindings: Map[String, Rdf#Node]): Future[Boolean] =
      store.execute(Command.ask(query, bindings))

  }

}

//trait SparqlUpdateEngine[Rdf <: RDF, M[_]] //todo: implement a version for updates


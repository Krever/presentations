package magicofintegrations.model

import eu.timepit.refined.api.Refined
import eu.timepit.refined.types.string.NonEmptyString
import io.circe.generic.JsonCodec

@JsonCodec
case class NoteV1(title: String, text: String)

object NoteV1 {

  def sample = NoteV1("My awesome title", "And my awesome text")

}

case class NoteV2(title: NonEmptyString)

case class Link(host: List[String], path: List[String])

import io.circe.syntax._

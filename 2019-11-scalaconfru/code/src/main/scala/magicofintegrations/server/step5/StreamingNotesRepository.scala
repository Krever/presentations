package magicofintegrations.server.step5

import cats.Applicative
import cats.effect.Bracket
import doobie.quill.DoobieContext
import doobie.util.transactor.Transactor
import magicofintegrations.model.Note

import scala.language.higherKinds

trait StreamingNotesRepository[F[_]] {
  def getAllStream(): fs2.Stream[F, Note]
}

package magicofintegrations.step4

import cats.Applicative
import cats.effect.Bracket
import doobie.quill.DoobieContext
import doobie.util.transactor.Transactor
import magicofintegrations.model.NoteV1

import scala.language.higherKinds

trait StreamingNotesRepository[F[_]] {
  def getAll(): fs2.Stream[F, NoteV1]
}

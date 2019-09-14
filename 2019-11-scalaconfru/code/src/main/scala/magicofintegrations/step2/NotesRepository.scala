package magicofintegrations.step2

import cats.Applicative
import cats.effect.Bracket
import doobie.quill.DoobieContext
import doobie.util.transactor.Transactor
import magicofintegrations.model.NoteV1

import scala.language.higherKinds

trait NotesRepository[F[_]] {
  def initialize(): F[Unit]
  def save(note: NoteV1): F[Unit]
  def getAll(): F[List[NoteV1]]
}

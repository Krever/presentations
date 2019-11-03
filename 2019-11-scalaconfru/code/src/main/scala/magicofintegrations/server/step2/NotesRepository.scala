package magicofintegrations.server.step2

import cats.Applicative
import cats.effect.Bracket
import doobie.quill.DoobieContext
import doobie.util.transactor.Transactor
import magicofintegrations.model.Note

import scala.language.higherKinds

trait NotesRepository[F[_]] {
  def initialize(): F[Unit]
  def save(note: Note): F[Unit]
  def getAll(): F[List[Note]]
}

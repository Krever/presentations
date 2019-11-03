package magicofintegrations.server.step2

import cats.Applicative
import cats.effect.Bracket
import doobie.quill.DoobieContext
import doobie.util.transactor.Transactor
import doobie.util.update.Update
import magicofintegrations.model.Note

import scala.language.higherKinds

class DoobieNotesRepository[F[_]: Bracket[?[_], Throwable]](xa: Transactor[F]) extends NotesRepository[F] {
  import cats.syntax.all._
  import doobie.implicits._

  def initialize(): F[Unit] = {
    val q = sql"create table if not exists notes_v1 ( title VARCHAR, text VARCHAR)".update
    q.run.transact(xa).as(())
  }

  override def save(note: Note): F[Unit] = {
    val q = sql"insert into notes_v1 (title, text) values (${note.title}, ${note.text})".update
    q.run.transact(xa).as(())
  }

  override def getAll(): F[List[Note]] = {
    val q = sql"select title, text from notes_v1".query[Note]
    q.to[List].transact(xa)
  }
}

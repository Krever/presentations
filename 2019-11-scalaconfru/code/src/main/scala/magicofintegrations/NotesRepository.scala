package magicofintegrations

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

class StaticNotesRepository[F[_]: Applicative] extends NotesRepository[F] {
  import cats.syntax.all._

  def initialize(): F[Unit]                = ().pure[F]
  override def save(note: NoteV1): F[Unit] = ().pure[F]
  override def getAll(): F[List[NoteV1]]   = List(NoteV1.sample).pure[F]
}

class DoobieNotesRepository[F[_]: Bracket[?[_], Throwable]](xa: Transactor[F]) extends NotesRepository[F] {
  import doobie.implicits._
  import cats.syntax.all._

  def initialize(): F[Unit] = {
    val q = sql"create table if not exists notes_v1 ( title VARCHAR, text VARCHAR)".update
    q.run.transact(xa).as(())
  }

  override def save(note: NoteV1): F[Unit] = {
    val q = sql"insert into notes_v1 (title, text) values (${note.title}, ${note.text})".update
    q.run.transact(xa).as(())
  }

  override def getAll(): F[List[NoteV1]] = {
    val q = sql"select title, text from notes_v1".query[NoteV1]
    q.to[List].transact(xa)
  }
}

import io.getquill._
class QuillNotesRepository[F[_]: Bracket[?[_], Throwable]](xa: Transactor[F], ctx: DoobieContext.H2[SnakeCase.type])
    extends NotesRepository[F] {
  import doobie.implicits._
  import cats.syntax.all._
  import ctx._

  private implicit val notesSchemaMeta = schemaMeta[NoteV1]("notes_v1")

  def initialize(): F[Unit] = {
    val q = sql"create table if not exists notes_v1 ( title VARCHAR, text VARCHAR)".update
    q.run.transact(xa).as(())
  }

  override def save(note: NoteV1): F[Unit] = {
    val q = quote {
      query[NoteV1].insert(lift(note))
    }
    run(q).transact(xa).as(())
  }

  override def getAll(): F[List[NoteV1]] = {
    val q = quote {
      query[NoteV1]
    }
    run(q).transact(xa)
  }
}

package magicofintegrations.step3

import cats.effect.Bracket
import doobie.quill.DoobieContext
import doobie.util.transactor.Transactor
import io.getquill._
import magicofintegrations.model.Note
import magicofintegrations.step2

import scala.language.higherKinds
class QuillNotesRepository[F[_]: Bracket[?[_], Throwable]](xa: Transactor[F], ctx: DoobieContext.H2[SnakeCase.type])
    extends step2.NotesRepository[F] {
  import cats.syntax.all._
  import ctx._
  import doobie.implicits._

  private implicit val notesSchemaMeta = schemaMeta[Note]("notes_v1")

  def initialize(): F[Unit] = {
    val q = sql"create table if not exists notes_v1 ( title VARCHAR, text VARCHAR)".update
    q.run.transact(xa).as(())
  }

  override def save(note: Note): F[Unit] = {
    val q = quote {
      query[Note].insert(lift(note))
    }
    run(q).transact(xa).as(())
  }

  override def getAll(): F[List[Note]] = {
    val q = quote {
      query[Note]
    }
    run(q).transact(xa)
  }
}

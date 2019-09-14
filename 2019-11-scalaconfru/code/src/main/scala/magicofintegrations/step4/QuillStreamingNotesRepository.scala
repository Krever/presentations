package magicofintegrations.step4

import cats.Applicative
import cats.effect.Bracket
import doobie.quill.DoobieContext
import doobie.util.transactor.Transactor
import magicofintegrations.model.NoteV1

import scala.language.higherKinds

import io.getquill._
class QuillStreamingNotesRepository[F[_]: Bracket[?[_], Throwable]](
    xa: Transactor[F],
    ctx: DoobieContext.H2[SnakeCase.type],
) extends StreamingNotesRepository[F] {
  import doobie.implicits._
  import cats.syntax.all._
  import ctx._

  private implicit val notesSchemaMeta = schemaMeta[NoteV1]("notes_v1")

  override def getAll(): fs2.Stream[F, NoteV1] = {
    stream {
      query[NoteV1]
    }.transact(xa)
  }
}

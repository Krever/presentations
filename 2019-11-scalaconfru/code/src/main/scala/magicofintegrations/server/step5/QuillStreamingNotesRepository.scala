package magicofintegrations.server.step5

import cats.effect.Bracket
import doobie.quill.DoobieContext
import doobie.util.transactor.Transactor
import io.getquill._
import magicofintegrations.model.Note
import magicofintegrations.server.step3.QuillNotesRepository

import scala.language.higherKinds
class QuillStreamingNotesRepository[F[_]: Bracket[?[_], Throwable]](
    xa: Transactor[F],
    ctx: DoobieContext.H2[SnakeCase.type],
) extends QuillNotesRepository[F](xa, ctx)
    with StreamingNotesRepository[F] {
  import ctx._
  import doobie.implicits._

  override def getAllStream(): fs2.Stream[F, Note] = {
    stream {
      query[Note]
    }.transact(xa)
  }
}

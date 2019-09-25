package magicofintegrations.step4

import cats.effect.{ConcurrentEffect, Sync}
import magicofintegrations.step2.{CirceNotesController, NotesRepository}
import cats.syntax.all._

class CirceStreamingNotesController[F[_]: Sync](
    repository: StreamingNotesRepository[F] with NotesRepository[F],
) extends CirceNotesController[F](repository)
    with StreamingNotesController[F] {
  import org.http4s._
  import dsl._
  import org.http4s.circe.CirceEntityEncoder._

  override def getNotesStream: HttpRoutes[F] = HttpRoutes.of[F] {
    case GET -> Root / "notes" =>
      val notes = repository.getAllStream()
      Ok(notes)
  }

  override def allRoutes(implicit c: ConcurrentEffect[F]): HttpRoutes[F] = super.allRoutes <+> getNotesStream
}

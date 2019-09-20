package magicofintegrations.step4

import cats.effect.Sync
import magicofintegrations.step0
import org.http4s.dsl.Http4sDsl

class CirceStreamingNotesController[F[_]: Sync](
    repository: StreamingNotesRepository[F],
    delegate: step0.NotesController[F],
) extends step0.NotesController[F] {
  import org.http4s._
  val dsl = Http4sDsl[F]
  import dsl._
  import org.http4s.circe.CirceEntityEncoder._

  override def saveNote: HttpRoutes[F] = delegate.saveNote

  override def getNotes: HttpRoutes[F] = HttpRoutes.of[F] {
    case GET -> Root / "notes" =>
      val notes = repository.getAll()
      Ok(notes)
  }
}

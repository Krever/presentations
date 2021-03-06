package magicofintegrations.server.step0

import cats.effect.Sync
import magicofintegrations.model.Note
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import cats.syntax.all._

class DummyNotesController[F[_]: Sync] extends NotesController[F] {
  import org.http4s._
  val dsl = Http4sDsl[F]
  import dsl._

  override def saveNote = HttpRoutes.of[F] {
    case POST -> Root / "notes" =>
      Ok("Hello")
  }
  override def getNotes = HttpRoutes.of[F] {
    case GET -> Root / "notes" =>
      Ok("World")
  }
}

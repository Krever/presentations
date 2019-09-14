package magicofintegrations

import cats.effect.Sync
import magicofintegrations.model.NoteV1
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl

trait NotesController[F[_]] {
  def saveNote: HttpRoutes[F]
  def getNotes: HttpRoutes[F]
}

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

class CirceNotesController[F[_]: Sync](repository: NotesRepository[F]) extends NotesController[F] {
  import cats.syntax.all._
  import org.http4s._
  val dsl = Http4sDsl[F]
  import dsl._
  import org.http4s.circe.CirceEntityDecoder._
  import org.http4s.circe.CirceEntityEncoder._

  override def saveNote: HttpRoutes[F] = HttpRoutes.of[F] {
    case req @ POST -> Root / "notes" =>
      for {
        note <- req.as[NoteV1]
        _    <- repository.save(note)
        resp <- Ok(s"Saved")
      } yield resp
  }

  override def getNotes: _root_.org.http4s.HttpRoutes[F] = HttpRoutes.of[F] {
    case GET -> Root / "notes" =>
      for {
        notes <- repository.getAll()
        resp  <- Ok(notes)
      } yield resp
  }
}

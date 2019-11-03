package magicofintegrations.server.step2

import cats.effect.Sync
import magicofintegrations.model.Note
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import magicofintegrations.server.step0

class CirceNotesController[F[_]: Sync](repository: NotesRepository[F]) extends step0.NotesController[F] {
  import cats.syntax.all._
  import org.http4s._
  val dsl = Http4sDsl[F]
  import dsl._
  import org.http4s.circe.CirceEntityDecoder._
  import org.http4s.circe.CirceEntityEncoder._

  override def saveNote: HttpRoutes[F] = HttpRoutes.of[F] {
    case req @ POST -> Root / "notes" =>
      for {
        note <- req.as[Note]
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

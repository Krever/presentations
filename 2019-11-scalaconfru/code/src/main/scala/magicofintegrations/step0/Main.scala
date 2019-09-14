package magicofintegrations.step0

import cats.effect._
import doobie.quill.DoobieContext
import doobie.util.transactor.Transactor

class Main[F[_]: ConcurrentEffect: Timer](controller: NotesController[F]) {

  def run: F[ExitCode] = {
    import cats.syntax.all._
    import org.http4s.implicits._
    import org.http4s.server.Router
    import org.http4s.server.blaze._
    val services      = controller.getNotes <+> controller.saveNote
    val httpApp       = Router("/" -> services).orNotFound
    val serverBuilder = BlazeServerBuilder[F].bindHttp(8080, "localhost").withHttpApp(httpApp)
    serverBuilder.serve.compile.drain
      .as(ExitCode.Success)
  }
}

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    val notesController: NotesController[IO] = new DummyNotesController[IO]
    new Main(notesController).run
  }
}

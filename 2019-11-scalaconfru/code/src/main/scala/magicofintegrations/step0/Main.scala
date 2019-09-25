package magicofintegrations.step0

import cats.effect._
import cats.syntax.all._
import org.http4s.HttpRoutes

class Main[F[_]: ConcurrentEffect: Timer](routes: HttpRoutes[F]) {

  def run: F[ExitCode] = {
    import org.http4s.implicits._
    import org.http4s.server.Router
    import org.http4s.server.blaze._
    val httpApp       = Router("/" -> routes).orNotFound
    val serverBuilder = BlazeServerBuilder[F].bindHttp(8080, "localhost").withHttpApp(httpApp)
    serverBuilder.serve.compile.drain
      .as(ExitCode.Success)
  }
}

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    val notesController: NotesController[IO] = new DummyNotesController[IO]
    new Main(notesController.allRoutes).run
  }
}

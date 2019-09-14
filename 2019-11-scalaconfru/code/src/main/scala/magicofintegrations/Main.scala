package magicofintegrations

import cats.effect.{ConcurrentEffect, ExitCode, IO, IOApp, Timer}
import doobie.quill.DoobieContext
import doobie.util.transactor.Transactor
import cats.syntax.all._

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {

    val notesController: NotesController[IO] = {
      val transactor = Transactor.fromDriverManager[IO](
        driver = "org.h2.Driver", // driver classname
        url    = "jdbc:h2:mem:MyDatabase;DB_CLOSE_DELAY=-1", // connect URL (driver-specific)
      )
//      val repo = new DoobieNotesRepository[IO](transactor)
      val repo = {
        val ctx = new DoobieContext.H2(io.getquill.SnakeCase)
        new QuillNotesRepository[IO](transactor, ctx)
      }
      repo.initialize().unsafeRunSync()
      new CirceNotesController[IO](repo)
    }

    startApp[IO](notesController)
  }

  def startApp[F[_]: ConcurrentEffect: Timer](controller: NotesController[F]): F[ExitCode] = {
    import cats.syntax.all._
    import org.http4s.server.blaze._
    import org.http4s.implicits._
    import org.http4s.server.Router
    val services      = controller.getNotes <+> controller.saveNote
    val httpApp       = Router("/" -> services).orNotFound
    val serverBuilder = BlazeServerBuilder[F].bindHttp(8080, "localhost").withHttpApp(httpApp)
    serverBuilder.serve.compile.drain
      .as(ExitCode.Success)
  }
}

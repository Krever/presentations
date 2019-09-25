package magicofintegrations.step2

import cats.effect._
import cats.syntax.all._
import doobie.util.transactor.Transactor
import magicofintegrations.step0
import org.http4s.HttpRoutes

class Main[F[_]: ConcurrentEffect: Timer](routes: HttpRoutes[F], repo: NotesRepository[F]) {

  def run: F[ExitCode] = {
    for {
      _    <- repo.initialize()
      code <- new step0.Main(routes).run
    } yield code
  }
}

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {

    val transactor = Transactor.fromDriverManager[IO](
      driver = "org.h2.Driver", // driver classname
      url    = "jdbc:h2:mem:MyDatabase;DB_CLOSE_DELAY=-1", // connect URL (driver-specific)
    )
    val repo = new DoobieNotesRepository[IO](transactor)

    val notesController: step0.NotesController[IO] = new CirceNotesController[IO](repo)

    new Main(notesController.allRoutes, repo).run
  }

}

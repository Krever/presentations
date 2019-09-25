package magicofintegrations.step1

import cats.effect._
import doobie.quill.DoobieContext
import doobie.util.transactor.Transactor
import magicofintegrations.step0

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {

    val notesController: step0.NotesController[IO] = new CirceDummyNotesController[IO]()

    new step0.Main(notesController.allRoutes).run

  }
}

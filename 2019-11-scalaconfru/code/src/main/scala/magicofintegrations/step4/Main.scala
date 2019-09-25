package magicofintegrations.step4

import cats.effect._
import doobie.quill.DoobieContext
import doobie.util.transactor.Transactor
import magicofintegrations.{step0, step2, step3}

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {

    val repo = {
      val ctx = new DoobieContext.H2(io.getquill.SnakeCase)
      new QuillStreamingNotesRepository[IO](step3.Main.transactor, ctx)
    }

    val notesController: step0.NotesController[IO] = new CirceStreamingNotesController[IO](repo)

    new step2.Main(notesController.allRoutes, repo).run
  }

}

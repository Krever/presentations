package magicofintegrations.server.step5

import cats.effect._
import doobie.quill.DoobieContext
import magicofintegrations.server.step3

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {

    val repo = {
      val ctx = new DoobieContext.H2(io.getquill.SnakeCase)
      new QuillStreamingNotesRepository[IO](step3.Main.transactor, ctx)
    }

    val notesController: magicofintegrations.server.step0.NotesController[IO] =
      new CirceStreamingNotesController[IO](repo)

    new magicofintegrations.server.step2.Main(notesController.allRoutes, repo).run
  }

}

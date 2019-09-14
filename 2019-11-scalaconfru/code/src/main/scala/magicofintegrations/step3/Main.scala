package magicofintegrations.step3

import cats.effect._
import doobie.quill.DoobieContext
import doobie.util.transactor.Transactor
import magicofintegrations.{step0, step2}

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {

    val transactor = Transactor.fromDriverManager[IO](
      driver = "org.h2.Driver", // driver classname
      url    = "jdbc:h2:mem:MyDatabase;DB_CLOSE_DELAY=-1", // connect URL (driver-specific)
    )
    val repo = {
      val ctx = new DoobieContext.H2(io.getquill.SnakeCase)
      new QuillNotesRepository[IO](transactor, ctx)
    }

    val notesController: step0.NotesController[IO] = new step2.CirceNotesController[IO](repo)

    new step2.Main(notesController, repo).run
  }

}

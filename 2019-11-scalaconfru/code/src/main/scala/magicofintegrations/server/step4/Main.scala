package magicofintegrations.server.step4

import cats.effect._
import doobie.quill.DoobieContext
import doobie.util.transactor.Transactor
import doobie.util.transactor.Transactor.Aux
import magicofintegrations.server.step3.QuillNotesRepository
import magicofintegrations.server.{step0, step2, step3}

object Main extends IOApp {

  def createTransactor(cfg: Config.Db): Aux[IO, Unit] = Transactor.fromDriverManager[IO](
    driver = "org.h2.Driver", // driver classname
    url    = cfg.jdbcUrl,
  )

  def createRepo(t: Transactor[IO]): step2.NotesRepository[IO] = {
    val ctx = new DoobieContext.H2(io.getquill.SnakeCase)
    new step3.QuillNotesRepository[IO](t, ctx)
  }

  def createServer(cfg: Config.Api, repo: step2.NotesRepository[IO]): Server[IO] = {
    val notesController: step0.NotesController[IO] = new step2.CirceNotesController[IO](repo)
    new ServerImpl[IO](cfg, notesController.allRoutes)
  }

  override def run(args: List[String]): IO[ExitCode] = {

    for {
      config     <- Config.value().load[IO]
      transactor = createTransactor(config.db)
      repo       = createRepo(transactor)
      server     = createServer(config.api, repo)
      _          <- repo.initialize()
      _          <- server.serve
    } yield ExitCode.Success

  }

}

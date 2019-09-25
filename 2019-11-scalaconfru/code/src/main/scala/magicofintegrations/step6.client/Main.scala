package magicofintegrations.step6.client

import java.nio.ByteBuffer

import cats.effect.{Console, ExitCode, IO, IOApp}
import magicofintegrations.step5.client.NotesCommand
import cats.syntax.all._
import com.softwaremill.sttp.SttpBackend
import com.softwaremill.sttp.asynchttpclient.fs2.AsyncHttpClientFs2Backend

object Main extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    val console: Console[IO]                                          = Console.io
    implicit val backend: SttpBackend[IO, fs2.Stream[IO, ByteBuffer]] = AsyncHttpClientFs2Backend[IO]()
    val handler                                                       = new ApiCmdHandler(SttpApiClient.factory[IO], console)
    (NotesCommand.command.parse(args, sys.env) match {
      case Left(help) => console.putStrLn(help.toString())
      case Right(value) =>
        handler.handle(value)
    }).as(ExitCode.Success)

  }

}

package magicofintegrations.client.step2

import java.nio.ByteBuffer

import cats.effect.Sync
import cats.{Functor, FunctorFilter, MonadError}
import com.softwaremill.sttp.SttpBackend
import magicofintegrations.model.Note
import cats.syntax.all._

class SttpApiClient[F[_]: Sync](url: String)(
    implicit backend: SttpBackend[F, fs2.Stream[F, ByteBuffer]],
) extends ApiClient[F] {
  import com.softwaremill.sttp._
  import com.softwaremill.sttp.circe._
  override def addNote(note: Note): F[Unit] = {
    sttp
      .post(uri"$url/notes")
      .body(note)
      .send()
      .map(_.body.leftMap(new RuntimeException(_)))
      .rethrow
      .as(())
  }

  override def getNotesStream(): fs2.Stream[F, Note] = {
    val response: F[fs2.Stream[F, ByteBuffer]] = sttp
      .get(uri"$url/notes")
      .response(asStream[fs2.Stream[F, ByteBuffer]])
      .send()
      .map(_.body.leftMap(new RuntimeException(_)))
      .rethrow

    fs2.Stream
      .eval(response)
      .flatten
      .flatMap(b => fs2.Stream.emits(b.array()))
      .through(io.circe.fs2.byteStreamParser[F])
      .map(_.as[Note])
      .rethrow
  }
}

object SttpApiClient {
  def factory[F[_]: Sync](
      implicit backend: SttpBackend[F, fs2.Stream[F, ByteBuffer]],
  ): ApiClient.Factory[F] =
    (host: String, port: Int) => new SttpApiClient[F](s"$host:$port")
}

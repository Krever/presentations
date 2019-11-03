package magicofintegrations.server.step5

import org.http4s.HttpRoutes

trait StreamingNotesController[F[_]] {
  def getNotesStream: HttpRoutes[F]
}

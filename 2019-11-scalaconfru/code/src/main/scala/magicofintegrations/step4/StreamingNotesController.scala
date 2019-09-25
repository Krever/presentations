package magicofintegrations.step4

import org.http4s.HttpRoutes

trait StreamingNotesController[F[_]] {
  def getNotesStream: HttpRoutes[F]
}
